package com.cyberfreight.taskapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0011\u0018\u0000 02\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0014J-\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0016\u00a2\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002J\b\u0010\'\u001a\u00020\u0012H\u0002J\b\u0010(\u001a\u00020\u0012H\u0002J\b\u0010)\u001a\u00020\u0012H\u0002J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u0014H\u0002J\u0010\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0014H\u0002J\u0012\u0010.\u001a\u00020\u00122\b\u0010/\u001a\u0004\u0018\u00010\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u00061"}, d2 = {"Lcom/cyberfreight/taskapp/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/cyberfreight/taskapp/databinding/ActivityMainBinding;", "geofenceManager", "Lcom/cyberfreight/taskapp/geofence/GeofenceManager;", "geofenceStatusReceiver", "Landroid/content/BroadcastReceiver;", "taskAdapter", "Lcom/cyberfreight/taskapp/ui/adapter/TaskAdapter;", "viewModel", "Lcom/cyberfreight/taskapp/ui/viewmodel/TaskViewModel;", "getViewModel", "()Lcom/cyberfreight/taskapp/ui/viewmodel/TaskViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkPermissions", "", "hasLocationPermissions", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestBackgroundLocationPermission", "requestLocationPermissions", "setupGeofence", "setupGeofenceStatusReceiver", "setupGeofencing", "setupObservers", "setupUI", "testApiCall", "updateConnectionStatus", "isOffline", "updateEmptyState", "isEmpty", "updateGeofenceStatus", "status", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.cyberfreight.taskapp.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.cyberfreight.taskapp.ui.adapter.TaskAdapter taskAdapter;
    private com.cyberfreight.taskapp.geofence.GeofenceManager geofenceManager;
    private android.content.BroadcastReceiver geofenceStatusReceiver;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private static final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 1002;
    @org.jetbrains.annotations.NotNull()
    public static final com.cyberfreight.taskapp.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.cyberfreight.taskapp.ui.viewmodel.TaskViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupGeofencing() {
    }
    
    private final void setupGeofenceStatusReceiver() {
    }
    
    private final void updateGeofenceStatus(java.lang.String status) {
    }
    
    private final void checkPermissions() {
    }
    
    private final boolean hasLocationPermissions() {
        return false;
    }
    
    private final void requestLocationPermissions() {
    }
    
    private final void requestBackgroundLocationPermission() {
    }
    
    private final void setupGeofence() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void updateConnectionStatus(boolean isOffline) {
    }
    
    private final void updateEmptyState(boolean isEmpty) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void testApiCall() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/cyberfreight/taskapp/MainActivity$Companion;", "", "()V", "BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE", "", "LOCATION_PERMISSION_REQUEST_CODE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}