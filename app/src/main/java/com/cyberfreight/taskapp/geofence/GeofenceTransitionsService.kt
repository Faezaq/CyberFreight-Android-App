package com.cyberfreight.taskapp.geofence

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceTransitionsService : IntentService("GeofenceTransitionsService") {
    
    companion object {
        const val TAG = "GeofenceTransitions"
        const val GEOFENCE_STATUS_ACTION = "com.cyberfreight.taskapp.GEOFENCE_STATUS_CHANGED"
        const val EXTRA_GEOFENCE_STATUS = "geofence_status"
        const val STATUS_ENTERED = "entered"
        const val STATUS_EXITED = "exited"
    }
    
    override fun onHandleIntent(intent: Intent?) {
        val geofencingEvent = intent?.let { GeofencingEvent.fromIntent(it) }
        
        if (geofencingEvent?.hasError() == true) {
            Log.e(TAG, "Geofencing error: ${geofencingEvent.errorCode}")
            return
        }
        
        val geofenceTransition = geofencingEvent?.geofenceTransition
        
        when (geofenceTransition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                Log.d(TAG, "User entered geofence")
                showToast("Welcome to the task zone! You're now in the predefined location.")
                broadcastGeofenceStatus(STATUS_ENTERED)
            }
            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                Log.d(TAG, "User exited geofence")
                showToast("You've left the task zone. Don't forget to complete your tasks!")
                broadcastGeofenceStatus(STATUS_EXITED)
            }
            else -> {
                Log.d(TAG, "Unknown geofence transition: $geofenceTransition")
            }
        }
    }
    
    private fun showToast(message: String) {
        // Show toast on main thread
        android.os.Handler(android.os.Looper.getMainLooper()).post {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun broadcastGeofenceStatus(status: String) {
        val intent = Intent(GEOFENCE_STATUS_ACTION).apply {
            putExtra(EXTRA_GEOFENCE_STATUS, status)
        }
        sendBroadcast(intent)
    }
} 