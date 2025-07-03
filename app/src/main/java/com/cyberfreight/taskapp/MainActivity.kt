package com.cyberfreight.taskapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberfreight.taskapp.databinding.ActivityMainBinding
import com.cyberfreight.taskapp.geofence.GeofenceManager
import com.cyberfreight.taskapp.geofence.GeofenceTransitionsService
import com.cyberfreight.taskapp.ui.adapter.TaskAdapter
import com.cyberfreight.taskapp.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var geofenceManager: GeofenceManager
    private lateinit var geofenceStatusReceiver: BroadcastReceiver
    
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
        private const val BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 1002
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        android.util.Log.d("MainActivity", "onCreate started")
        setupUI()
        setupObservers()
        setupGeofencing()
        setupGeofenceStatusReceiver()
        checkPermissions()
        

        
        // Test API call directly
        testApiCall()
    }
    
    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        
        taskAdapter = TaskAdapter()
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
        
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshTasks()
        }
    }
    
    private fun setupObservers() {
        android.util.Log.d("MainActivity", "Setting up observers")
        
        viewModel.tasks.observe(this) { tasks ->
            android.util.Log.d("MainActivity", "Received ${tasks.size} tasks in UI")
            taskAdapter.submitList(tasks)
            updateEmptyState(tasks.isEmpty())
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            android.util.Log.d("MainActivity", "Loading state: $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.swipeRefreshLayout.isRefreshing = isLoading
        }
        
        viewModel.error.observe(this) { error ->
            android.util.Log.d("MainActivity", "Error state: $error")
            error?.let {
                binding.errorText.text = it
                binding.errorText.visibility = View.VISIBLE
                // Don't show Toast for network errors to avoid spam
            } ?: run {
                binding.errorText.visibility = View.GONE
            }
        }
        
        viewModel.isOffline.observe(this) { isOffline ->
            android.util.Log.d("MainActivity", "isOffline LiveData changed to: $isOffline")
            updateConnectionStatus(isOffline)
        }
        
        // Check current value immediately after setting up observer
        android.util.Log.d("MainActivity", "Current isOffline value: ${viewModel.isOffline.value}")
        viewModel.isOffline.value?.let { isOffline ->
            android.util.Log.d("MainActivity", "Manually triggering updateConnectionStatus with current value: $isOffline")
            updateConnectionStatus(isOffline)
        }
    }
    
    private fun setupGeofencing() {
        geofenceManager = GeofenceManager(this)
    }
    
    private fun setupGeofenceStatusReceiver() {
        geofenceStatusReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == GeofenceTransitionsService.GEOFENCE_STATUS_ACTION) {
                    val status = intent.getStringExtra(GeofenceTransitionsService.EXTRA_GEOFENCE_STATUS)
                    updateGeofenceStatus(status)
                }
            }
        }
        
        val filter = IntentFilter(GeofenceTransitionsService.GEOFENCE_STATUS_ACTION)
        registerReceiver(geofenceStatusReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }
    
    private fun updateGeofenceStatus(status: String?) {
        android.util.Log.d("MainActivity", "Updating geofence status: $status")
        when (status) {
            GeofenceTransitionsService.STATUS_ENTERED -> {
                // Not enough time but for better usability I could create icons instead of adding them directly in text below.
                binding.geofenceStatus.text = "📍 IN GEOFENCE AREA"
                binding.geofenceStatus.setTextColor(resources.getColor(R.color.geofence_active, null))
                android.util.Log.d("MainActivity", "Set geofence status to: IN GEOFENCE AREA")
            }
            GeofenceTransitionsService.STATUS_EXITED -> {
                binding.geofenceStatus.text = "📍 Geofence Active"
                binding.geofenceStatus.setTextColor(resources.getColor(R.color.geofence_active, null))
                android.util.Log.d("MainActivity", "Set geofence status to: Geofence Active")
            }
        }
        // Force the UI to update
        binding.geofenceStatus.invalidate()
    }
    
    private fun checkPermissions() {
        if (hasLocationPermissions()) {
            setupGeofence()
        } else {
            requestLocationPermissions()
        }
    }
    
    private fun hasLocationPermissions(): Boolean {
        val fineLocation = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        val coarseLocation = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        val backgroundLocation = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        android.util.Log.d("MainActivity", "Location permissions - Fine: $fineLocation, Coarse: $coarseLocation, Background: $backgroundLocation")
        
        return fineLocation && coarseLocation && backgroundLocation
    }
    
    private fun requestLocationPermissions() {
        // First request basic location permissions
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }
    
    private fun requestBackgroundLocationPermission() {
        // Request background location permission separately
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
            BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE
        )
    }
    
    private fun setupGeofence() {
        if (hasLocationPermissions()) {
            geofenceManager.createGeofence()
            // Test the geofence status update by simulating an enter event
            android.util.Log.d("MainActivity", "Testing geofence status update")
            updateGeofenceStatus(GeofenceTransitionsService.STATUS_ENTERED)
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && 
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // Basic location permissions granted, now request background location
                    android.util.Log.d("MainActivity", "Basic location permissions granted, requesting background location")
                    requestBackgroundLocationPermission()
                } else {
                    Toast.makeText(
                        this,
                        "Location permission required for geofencing",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && 
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    android.util.Log.d("MainActivity", "Background location permission granted, setting up geofence")
                    setupGeofence()
                } else {
                    Toast.makeText(
                        this,
                        "Background location permission required for geofencing to work",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    
    private fun updateConnectionStatus(isOffline: Boolean) {
        android.util.Log.d("MainActivity", "updateConnectionStatus called with isOffline: $isOffline")
        binding.statusIcon.setImageResource(
            if (isOffline) android.R.drawable.ic_dialog_alert
            else android.R.drawable.ic_menu_share
        )
        binding.statusText.text = if (isOffline) "Offline - Using cached data" else "Online"
        android.util.Log.d("MainActivity", "Status text set to: ${binding.statusText.text}")
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyText.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }
    
    override fun onDestroy() {
        super.onDestroy()
        geofenceManager.removeGeofence()
        try {
            unregisterReceiver(geofenceStatusReceiver)
        } catch (e: Exception) {
            // Receiver might not be registered
        }
    }
    
    private fun testApiCall() {
        lifecycleScope.launch {
            try {
                android.util.Log.d("MainActivity", "Testing API call...")
                val apiService = com.cyberfreight.taskapp.data.api.RetrofitClient.apiService
                val tasks = apiService.getTasks()
                android.util.Log.d("MainActivity", "Direct API call successful: ${tasks.size} tasks")
                Toast.makeText(this@MainActivity, "API call successful: ${tasks.size} tasks", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                android.util.Log.e("MainActivity", "Direct API call failed: ${e.message}")
                Toast.makeText(this@MainActivity, "API call failed due to no internet connection.", Toast.LENGTH_LONG).show()
            }
        }
    }
} 