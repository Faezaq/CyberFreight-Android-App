package com.cyberfreight.taskapp.geofence;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0002\u00a8\u0006\r"}, d2 = {"Lcom/cyberfreight/taskapp/geofence/GeofenceTransitionsService;", "Landroid/app/IntentService;", "()V", "broadcastGeofenceStatus", "", "status", "", "onHandleIntent", "intent", "Landroid/content/Intent;", "showToast", "message", "Companion", "app_debug"})
public final class GeofenceTransitionsService extends android.app.IntentService {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "GeofenceTransitions";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GEOFENCE_STATUS_ACTION = "com.cyberfreight.taskapp.GEOFENCE_STATUS_CHANGED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_GEOFENCE_STATUS = "geofence_status";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STATUS_ENTERED = "entered";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STATUS_EXITED = "exited";
    @org.jetbrains.annotations.NotNull()
    public static final com.cyberfreight.taskapp.geofence.GeofenceTransitionsService.Companion Companion = null;
    
    public GeofenceTransitionsService() {
        super(null);
    }
    
    @java.lang.Override()
    protected void onHandleIntent(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    private final void showToast(java.lang.String message) {
    }
    
    private final void broadcastGeofenceStatus(java.lang.String status) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/cyberfreight/taskapp/geofence/GeofenceTransitionsService$Companion;", "", "()V", "EXTRA_GEOFENCE_STATUS", "", "GEOFENCE_STATUS_ACTION", "STATUS_ENTERED", "STATUS_EXITED", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}