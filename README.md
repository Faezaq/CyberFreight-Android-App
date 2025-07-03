# CyberFreight - Android Task Management App

A modern Android application built with Kotlin that demonstrates MVVM architecture, API integration, offline caching, and geofencing capabilities.

## Features

### Core Functionality
- ✅ **Task Management**: Fetch and display tasks from JSONPlaceholder API
- ✅ **Offline Support**: Local caching with Room database for offline access
- ✅ **Real-time Status**: Online/offline status indicator
- ✅ **Pull-to-Refresh**: Swipe down to refresh task data
- ✅ **Error Handling**: User-friendly error messages with automatic dismissal

### Geofencing
- ✅ **Location-based Notifications**: Toast notifications when entering/exiting geofence
- ✅ **Real-time Status Indicator**: Visual indicator in status bar showing geofence state
- ✅ **Current Location Detection**: Automatically creates geofence near user's location
- ✅ **Permission Handling**: Proper Android 1s0+ background location permission flow

### Architecture
- ✅ **MVVM Pattern**: ViewModel with LiveData for reactive UI
- ✅ **Repository Pattern**: Clean separation of data sources
- ✅ **Room Database**: Local SQLite database with DAO
- ✅ **Retrofit**: HTTP client for API communication
- ✅ **Coroutines**: Asynchronous programming with Kotlin coroutines

## Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 26+ (API level 26)
- Google Play Services (for geofencing)
- Internet connection (for initial data fetch)

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd CyberFreight
```

### 2. Open in Android Studio
- Launch Android Studio
- Select "Open an existing Android Studio project"
- Navigate to the CyberFreight folder and select it

### 3. Sync Dependencies
- Wait for Gradle sync to complete
- If prompted, update Gradle version or Android Gradle Plugin

### 4. Build and Run
- Connect an Android device or start an emulator
- Click the "Run" button (green play icon) in Android Studio
- Select your target device and click "OK"

## Permission Setup

### Location Permissions
The app requires location permissions for geofencing functionality:

1. **First Launch**: The app will request basic location permissions
2. **Background Location**: After granting basic permissions, the app will request background location access
3. **Grant All Permissions**: Allow all location permissions when prompted

### Manual Permission Setup (if needed)
If permissions aren't requested automatically:
1. Go to **Settings → Apps → CyberFreight → Permissions**
2. Enable **Location** permission
3. Set location access to **"Allow all the time"**

## Usage

### Basic Task Management
1. **View Tasks**: Tasks are automatically loaded when the app starts
2. **Offline Mode**: Tasks are cached locally and available without internet
3. **Refresh Data**: Pull down on the task list to refresh from the server
4. **Status Indicator**: Check the top status bar for online/offline status

### Geofencing
1. **Automatic Setup**: Geofence is created automatically near your current location
2. **Status Indicator**: The status bar shows "📍 Geofence Active" or "📍 IN GEOFENCE AREA"
3. **Notifications**: Toast messages appear when entering/exiting the geofence area
4. **Location Updates**: The geofence updates based on your current location

## Troubleshooting

### Geofencing Issues

#### Error 1004: GEOFENCE_NOT_AVAILABLE
**Cause**: Device doesn't support geofencing or has restrictions
**Solutions**:
1. **Disable Battery Optimization**:
   - Settings → Apps → CyberFreight → Battery → Battery optimization
   - Set to "Don't optimize"
2. **Check Location Services**:
   - Settings → Location → Turn on location services
3. **Update Google Play Services**:
   - Play Store → Search "Google Play Services" → Update

#### Missing Background Location Permission
**Cause**: Android 10+ requires explicit background location permission
**Solution**:
1. Settings → Apps → CyberFreight → Permissions → Location
2. Set to "Allow all the time"

### Network Issues

#### No Internet Connection
- App will work offline with cached data
- Status bar will show "Offline - Using cached data"
- Pull-to-refresh will show error message temporarily

#### API Errors
- Check internet connection
- Verify JSONPlaceholder API is accessible
- Error messages are shown for 5 seconds then dismissed

### Build Issues

#### Gradle Sync Failed
1. **Update Gradle**: File → Settings → Build Tools → Gradle
2. **Clean Project**: Build → Clean Project
3. **Invalidate Caches**: File → Invalidate Caches and Restart

#### Missing Dependencies
1. **Sync Project**: File → Sync Project with Gradle Files
2. **Check Internet**: Ensure Android Studio can download dependencies
3. **Update Android Studio**: Use latest version

## Project Structure

```
app/src/main/java/com/cyberfreight/taskapp/
├── data/
│   ├── api/           # Retrofit API service
│   ├── local/         # Room database and DAO
│   ├── model/         # Data models
│   └── repository/    # Repository pattern implementation
├── geofence/          # Geofencing functionality
├── ui/
│   ├── adapter/       # RecyclerView adapter
│   └── viewmodel/     # ViewModel classes
└── MainActivity.kt    # Main activity
```

## Dependencies

### Core Dependencies
- **AndroidX**: Core Android libraries
- **Material Design**: UI components
- **Room**: Local database
- **Retrofit**: HTTP client
- **Coroutines**: Asynchronous programming

### Location Services
- **Google Play Services Location**: Geofencing and location services
- **Google Play Services Base**: Core Google Play Services

## API Endpoint

The app uses the JSONPlaceholder API for demonstration:
- **Base URL**: `https://jsonplaceholder.typicode.com`
- **Endpoint**: `/todos`
- **Method**: GET
- **Response**: Array of task objects

## Testing

### Manual Testing
1. **Online Mode**: Test with internet connection
2. **Offline Mode**: Enable airplane mode and test cached data
3. **Geofencing**: Move device to trigger geofence events
4. **Permissions**: Test permission flow by uninstalling and reinstalling

### Device Compatibility
- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 34 (Android 14)
- **Geofencing**: Requires Google Play Services and location hardware

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Review the logcat output for error details
3. Ensure all prerequisites are met
4. Test on a different device if possible
