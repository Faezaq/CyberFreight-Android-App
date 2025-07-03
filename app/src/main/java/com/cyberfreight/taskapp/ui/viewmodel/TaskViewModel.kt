package com.cyberfreight.taskapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cyberfreight.taskapp.data.model.Task
import com.cyberfreight.taskapp.data.repository.TaskRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TaskRepository(application)
    
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private val _isOffline = MutableLiveData<Boolean>(true) // Start as offline until we confirm connection
    val isOffline: LiveData<Boolean> = _isOffline
    
    init {
        // Check initial network status
        val networkAvailable = isNetworkAvailable()
        _isOffline.value = !networkAvailable
        loadTasks()
    }
    
    fun loadTasks() {
        viewModelScope.launch {
            android.util.Log.d("TaskViewModel", "Loading tasks")
            _isLoading.value = true
            _error.value = null
            
            repository.getTasks()
                .catch { e ->
                    android.util.Log.e("TaskViewModel", "Error in flow: ${e.message}")
                    // Do not show error on UI if there is no data; just log it
                    _isOffline.value = true // Set offline status when network fails
                    _isLoading.value = false
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { tasks ->
                            android.util.Log.d("TaskViewModel", "Received ${tasks.size} tasks")
                            _tasks.value = tasks
                            // Don't change offline status based on data success - keep it based on network availability
                            android.util.Log.d("TaskViewModel", "Keeping isOffline as: ${_isOffline.value}")
                            _error.value = null
                        },
                        onFailure = { exception ->
                            android.util.Log.e("TaskViewModel", "Failure: ${exception.message}")
                            // Only show error if we have cached data
                            if (!_tasks.value.isNullOrEmpty()) {
                                showTemporaryError("Unable to load new data. Please check your connection.")
                            } else {
                                _error.value = null // Don't show error if we have no data
                            }
                            _isOffline.value = true
                        }
                    )
                    _isLoading.value = false
                }
        }
    }
    
    fun refreshTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            val result = repository.refreshTasks()
            result.fold(
                onSuccess = { tasks ->
                    _tasks.value = tasks
                    _isOffline.value = false // Refresh success means we're online
                    _error.value = null
                },
                onFailure = { exception ->
                    android.util.Log.e("TaskViewModel", "Refresh failed: ${exception.message}")
                    // Only show error if we have cached data
                    if (!_tasks.value.isNullOrEmpty()) {
                        showTemporaryError("Unable to refresh. Please check your connection.")
                    } else {
                        _error.value = null // Don't show error if we have no data
                    }
                    _isOffline.value = true
                }
            )
            _isLoading.value = false
        }
    }
    
    fun clearError() {
        _error.value = null
    }
    
    private fun showTemporaryError(message: String) {
        _error.value = message
        viewModelScope.launch {
            kotlinx.coroutines.delay(5000) // 5 seconds
            if (_error.value == message) { // Only clear if it's still the same message
                _error.value = null
            }
        }
    }
    
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        android.util.Log.d("TaskViewModel", "isNetworkAvailable - Active network: $network")
        
        if (network == null) {
            android.util.Log.d("TaskViewModel", "isNetworkAvailable - No active network, returning false")
            return false
        }
        
        val activeNetwork = connectivityManager.getNetworkCapabilities(network)
        android.util.Log.d("TaskViewModel", "isNetworkAvailable - Network capabilities: $activeNetwork")
        
        if (activeNetwork == null) {
            android.util.Log.d("TaskViewModel", "isNetworkAvailable - No network capabilities, returning false")
            return false
        }
        
        val hasWifi = activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        val hasCellular = activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        val hasEthernet = activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        
        android.util.Log.d("TaskViewModel", "isNetworkAvailable - Has WiFi: $hasWifi, Has Cellular: $hasCellular, Has Ethernet: $hasEthernet")
        
        val result = when {
            hasWifi -> true
            hasCellular -> true
            hasEthernet -> true
            else -> false
        }
        
        android.util.Log.d("TaskViewModel", "isNetworkAvailable - Final result: $result")
        return result
    }
} 