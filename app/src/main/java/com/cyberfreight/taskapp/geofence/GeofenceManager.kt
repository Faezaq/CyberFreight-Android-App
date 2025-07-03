package com.cyberfreight.taskapp.geofence

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import android.location.LocationManager


class GeofenceManager(private val context: Context) {
    private val geofencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)
    
    // Hardcoded geofence location (San Francisco, CA)
    private val GEOFENCE_LATITUDE = 37.7749
    private val GEOFENCE_LONGITUDE = -122.4194
    private val GEOFENCE_RADIUS = 1000f // 1 kilometer (larger radius for testing)
    
    companion object {
        const val GEOFENCE_ID = "TASK_APP_GEOFENCE"
        const val TAG = "GeofenceManager"
    }
    
    private fun isPlayServicesAvailable(): Boolean {
        val resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        return resultCode == ConnectionResult.SUCCESS
    }
    
    private fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        val passiveEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
        
        Log.d(TAG, "Location providers - GPS: $gpsEnabled, Network: $networkEnabled, Passive: $passiveEnabled")
        
        return gpsEnabled || networkEnabled || passiveEnabled
    }
    
    fun createGeofence() {
        Log.d(TAG, "Creating geofence...")
        
        // Test if geofencing is supported at all
        val isGeofencingSupported = com.google.android.gms.location.LocationServices.getGeofencingClient(context) != null
        Log.d(TAG, "Geofencing client available: $isGeofencingSupported")
        
        // Check if we can get location updates (alternative test)
        try {
            val fusedLocationClient = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
            Log.d(TAG, "Fused location client available: ${fusedLocationClient != null}")
        } catch (e: Exception) {
            Log.e(TAG, "Fused location client error: ${e.message}")
        }
        
        // Check if Google Play Services is available
        if (!isPlayServicesAvailable()) {
            Log.e(TAG, "Google Play Services not available")
            android.os.Handler(android.os.Looper.getMainLooper()).post {
                android.widget.Toast.makeText(
                    context, 
                    "Google Play Services not available. Please update Google Play Services.", 
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        Log.d(TAG, "Google Play Services is available")
        
        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) 
            != PackageManager.PERMISSION_GRANTED) {
            Log.w(TAG, "Location permission not granted")
            android.os.Handler(android.os.Looper.getMainLooper()).post {
                android.widget.Toast.makeText(
                    context, 
                    "Location permission required for geofencing.", 
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        Log.d(TAG, "Location permission granted")
        
        // Check if location is enabled
        if (!isLocationEnabled()) {
            Log.e(TAG, "Location services not enabled")
            android.os.Handler(android.os.Looper.getMainLooper()).post {
                android.widget.Toast.makeText(
                    context, 
                    "Please enable location services in device settings.", 
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        Log.d(TAG, "Location services enabled")
        
        // Check for battery optimization
        try {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as android.os.PowerManager
            val packageName = context.packageName
            val isIgnoringBatteryOptimizations = powerManager.isIgnoringBatteryOptimizations(packageName)
            Log.d(TAG, "Battery optimization ignored for app: $isIgnoringBatteryOptimizations")
            
            if (!isIgnoringBatteryOptimizations) {
                Log.w(TAG, "App is being battery optimized - this may affect geofencing")
                android.os.Handler(android.os.Looper.getMainLooper()).post {
                    android.widget.Toast.makeText(
                        context, 
                        "Please disable battery optimization for this app in device settings for geofencing to work.", 
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error checking battery optimization: ${e.message}")
        }
        
        // Test if we can get current location
        try {
            val fusedLocationClient = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    Log.d(TAG, "Current location: ${location.latitude}, ${location.longitude}")
                } else {
                    Log.w(TAG, "Could not get current location")
                }
            }.addOnFailureListener { e ->
                Log.e(TAG, "Failed to get current location: ${e.message}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting current location: ${e.message}")
        }
        
        // Try with current location first, then fallback to San Francisco
        val fusedLocationClient = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                Log.d(TAG, "Creating geofence near current location: ${location.latitude}, ${location.longitude}")
                createGeofenceAtLocation(location.latitude, location.longitude)
            } else {
                Log.d(TAG, "No current location, using San Francisco")
                createGeofenceAtLocation(GEOFENCE_LATITUDE, GEOFENCE_LONGITUDE)
            }
        }.addOnFailureListener { e ->
            Log.e(TAG, "Failed to get location, using San Francisco: ${e.message}")
            createGeofenceAtLocation(GEOFENCE_LATITUDE, GEOFENCE_LONGITUDE)
        }
    }
    
    private fun createGeofenceAtLocation(latitude: Double, longitude: Double) {
        // Try with absolute minimum requirements
        val geofence = Geofence.Builder()
            .setRequestId(GEOFENCE_ID)
            .setCircularRegion(
                latitude,
                longitude,
                100f // Try with minimum radius
            )
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER) // Only ENTER transition
            .build()
        
        val geofencingRequest = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence)
            .build()
        
        val geofencePendingIntent: PendingIntent = PendingIntent.getService(
            context,
            0,
            Intent(context, GeofenceTransitionsService::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        
        Log.d(TAG, "Adding geofence to client at location: $latitude, $longitude with radius: 100m (minimum)")
        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
            .addOnSuccessListener {
                Log.d(TAG, "Geofence added successfully")
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        context, 
                        "Geofence activated at current location (${String.format("%.4f", latitude)}, ${String.format("%.4f", longitude)})", 
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Failed to add geofence: ${e.message}")
                Log.e(TAG, "Error details: ${e.toString()}")
                
                // Check for specific error codes
                when {
                    e.message?.contains("1004") == true -> {
                        Log.e(TAG, "GEOFENCE_NOT_AVAILABLE - Geofencing service not available on this device")
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                context, 
                                "Geofencing not available. Try disabling battery optimization for this app in device settings.", 
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    e.message?.contains("1005") == true -> {
                        Log.e(TAG, "GEOFENCE_TOO_MANY_GEOFENCES - Too many geofences")
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                context, 
                                "Too many geofences. Please remove some existing geofences.", 
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    e.message?.contains("1006") == true -> {
                        Log.e(TAG, "GEOFENCE_TOO_MANY_PENDING_INTENTS - Too many pending intents")
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                context, 
                                "Too many pending geofence intents. Please try again later.", 
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    else -> {
                        Log.e(TAG, "Unknown geofence error: ${e.message}")
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                context, 
                                "Failed to set up geofencing: ${e.message}", 
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
    }
    
    fun removeGeofence() {
        geofencingClient.removeGeofences(
            PendingIntent.getService(
                context,
                0,
                Intent(context, GeofenceTransitionsService::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        ).addOnSuccessListener {
            Log.d(TAG, "Geofence removed successfully")
        }.addOnFailureListener { e ->
            Log.e(TAG, "Failed to remove geofence: ${e.message}")
        }
    }
} 