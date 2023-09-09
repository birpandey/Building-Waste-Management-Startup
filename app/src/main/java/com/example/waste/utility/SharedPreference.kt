package com.example.waste.utility

import androidx.core.content.edit

class SharedPreference : SharedInit() {
    interface Keys {
            interface User {
                companion object {
                    const val id = "User.id"
                    const val name = "User.name"
                    const val latitude = "User.latitude"
                    const val longitude = "User.longitude"
                }
            }

            /*var isFirstTimeLaunch: Boolean
            get() = prefs?.getBoolean(IS_FIRST_TIME_LAUNCH, true)!!
            set(isFirstTime) {
                put(IS_FIRST_TIME_LAUNCH, isFirstTime)?.apply()
            }*/

           var userLatitude: String?
                get() = prefs?.getString(User.latitude, "-")
                set(code) {
                    put(User.latitude, code)?.apply()
                }
            var userLongitude: String?
                get() = prefs?.getString(User.longitude, "-")
                set(code) {
                    put(User.longitude, code)?.apply()
                }

            /* fun onLogout() {
            if (isUserLogin) {
                isAppSyncSignOut = false
                clear()
            }
        }*/
        }
    fun getUserLatitude(): Double {
        val defaultValue = -1.0 // or any other default value you prefer
        return prefs?.getFloat(Keys.User.latitude, defaultValue.toFloat())?.toDouble() ?: defaultValue
    }

    fun setUserLatitude(latitude: Double) {
        prefs?.edit {
            putFloat(Keys.User.latitude, latitude.toFloat())
            apply()
        }
    }

    fun getUserLongitude(): Double {
        val defaultValue = -1.0 // or any other default value you prefer
        return prefs?.getFloat(Keys.User.longitude, defaultValue.toFloat())?.toDouble() ?: defaultValue
    }

    fun setUserLongitude(longitude: Double) {
        prefs?.edit {
            putFloat(Keys.User.longitude, longitude.toFloat())
            apply()
        }
    }

}