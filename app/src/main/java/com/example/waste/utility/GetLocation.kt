package com.example.waste.utility

import android.app.Activity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.SettingsClient

class GetLocation(private var activity: Activity) {
    // bunch of location related apis
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mSettingsClient: SettingsClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mLocationSettingsRequest: LocationSettingsRequest? = null
    private var mLocationCallback: LocationCallback? = null
    var latitude = 0.0
    var longitude = 0.0
    private val sharedPrefUtils: SharedPreference = SharedPreference()
    fun init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        mLocationRequest = LocationRequest()
        mLocationRequest!!.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest!!.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        mSettingsClient = LocationServices.getSettingsClient(activity)
        mLocationSettingsRequest = builder.build()
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                        sharedPrefUtils.setUserLatitude(location.latitude)
                        sharedPrefUtils.setUserLongitude(location.longitude)
                    }
                }
            }
        }
    }

    /*fun startLocationUpdates() {
        mSettingsClient
            .checkLocationSettings(mLocationSettingsRequest!!)
            .addOnSuccessListener(activity, object : OnSuccessListener<LocationSettingsResponse?> {
                @SuppressLint("MissingPermission")
                override fun onSuccess(locationSettingsResponse: LocationSettingsResponse?) {
                    Log.i(TAG, "All location settings are satisfied.")
                    mFusedLocationClient!!.requestLocationUpdates(
                        mLocationRequest!!,
                        mLocationCallback!!, Looper.myLooper()
                    )
                    lastKnownLocaiton
                }
            })
            .addOnFailureListener(activity) { e ->
                val statusCode = (e as ApiException).statusCode
                when (statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        Log.i(
                            TAG,
                            "Location settings are not satisfied. Attempting to upgrade " +
                                    "location settings "
                        )
                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the
                            // result in onActivityResult().
                            val rae = e as ResolvableApiException
                            rae.startResolutionForResult(
                                activity,
                                REQUEST_CHECK_SETTINGS
                            )
                        } catch (sie: SendIntentException) {
                            Log.i(
                                TAG,
                                "PendingIntent unable to execute requestLiveDataCallback."
                            )
                        }
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        val errorMessage = "Location settings are inadequate, and cannot be " +
                                "fixed here. Fix in Settings."
                        Log.e(TAG, errorMessage)
                    }
                }
            }
    }*/

    /*fun stopLocationUpdates() {
        // Removing location updates
        mFusedLocationClient
            .removeLocationUpdates(mLocationCallback!!)
            .addOnCompleteListener(
                activity
            ) { }
    }*/

    /*  private val lastKnownLocaiton: Unit
          private get() {
              if (ActivityCompat.checkSelfPermission(
                      activity,
                      Manifest.permission.ACCESS_FINE_LOCATION
                  ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                      activity, Manifest.permission.ACCESS_COARSE_LOCATION
                  ) != PackageManager.PERMISSION_GRANTED
              ) {
                  // TODO: Consider calling
                  //    ActivityCompat#requestPermissions
                  // here to requestLiveDataCallback the missing permissions, and then overriding
                  //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                  //                                          int[] grantResults)
                  // to handle the case where the user grants the permission. See the documentation
                  // for ActivityCompat#requestPermissions for more details.
                  return
              }
              mFusedLocationClient!!.lastLocation.addOnSuccessListener(
                  activity
              ) { location ->
                  if (location != null) {
                      SharedPrefUtils.setUserLatitude("" + location.latitude)
                      SharedPrefUtils.setUserLongitude("" + location.longitude)
                  }
              }
          }
  */
    companion object {
        const val REQUEST_CHECK_SETTINGS = 100
        private val TAG = GetLocation::class.java.simpleName

        // location updates interval - 2sec
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 2000

        // fastest updates interval - 1 sec
        // location updates will be received if another app is requesting the locations
        // than your app can handle
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS: Long = 1000
        private var single_instance: GetLocation? = null
        fun getInstance(activity: Activity): GetLocation? {
            if (single_instance == null) {
                single_instance = GetLocation(activity)
            }
            single_instance!!.activity = activity
            return single_instance
        }
    }
}