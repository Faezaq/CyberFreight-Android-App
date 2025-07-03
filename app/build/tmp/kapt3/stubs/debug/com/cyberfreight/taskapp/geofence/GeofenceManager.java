package com.cyberfreight.taskapp.geofence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0006\u0010\u0014\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/cyberfreight/taskapp/geofence/GeofenceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "GEOFENCE_LATITUDE", "", "GEOFENCE_LONGITUDE", "GEOFENCE_RADIUS", "", "geofencingClient", "Lcom/google/android/gms/location/GeofencingClient;", "createGeofence", "", "createGeofenceAtLocation", "latitude", "longitude", "isLocationEnabled", "", "isPlayServicesAvailable", "removeGeofence", "Companion", "app_debug"})
public final class GeofenceManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.GeofencingClient geofencingClient = null;
    private final double GEOFENCE_LATITUDE = 37.7749;
    private final double GEOFENCE_LONGITUDE = -122.4194;
    private final float GEOFENCE_RADIUS = 1000.0F;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GEOFENCE_ID = "TASK_APP_GEOFENCE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "GeofenceManager";
    @org.jetbrains.annotations.NotNull()
    public static final com.cyberfreight.taskapp.geofence.GeofenceManager.Companion Companion = null;
    
    public GeofenceManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final boolean isPlayServicesAvailable() {
        return false;
    }
    
    private final boolean isLocationEnabled() {
        return false;
    }
    
    public final void createGeofence() {
    }
    
    private final void createGeofenceAtLocation(double latitude, double longitude) {
    }
    
    public final void removeGeofence() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/cyberfreight/taskapp/geofence/GeofenceManager$Companion;", "", "()V", "GEOFENCE_ID", "", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}