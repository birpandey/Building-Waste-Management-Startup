package com.example.waste.utility

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.preference.PreferenceManager
import android.text.TextUtils
import java.util.Arrays

open class SharedInit {
    companion object {
        var prefs: SharedPreferences? = null
            private set
        private var sPrefsEditor: SharedPreferences.Editor? = null

        fun init(context: Context?) {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            sPrefsEditor = prefs?.edit()
        }

        fun destroy() {
            sPrefsEditor!!.apply()
            sPrefsEditor = null
            prefs = null
        }

        fun remove(key: String?) {
            sPrefsEditor!!.remove(key)
            sPrefsEditor!!.commit()
            sPrefsEditor!!.apply()
        }

        fun registerListener(listener: OnSharedPreferenceChangeListener?) {
            prefs!!.registerOnSharedPreferenceChangeListener(listener)
        }

        fun unregisterListener(listener: OnSharedPreferenceChangeListener?) {
            prefs!!.unregisterOnSharedPreferenceChangeListener(listener)
        }

        fun put(key: String?, value: Boolean?): SharedPreferences.Editor? {
            if (value == null) {
                sPrefsEditor!!.remove(key)
            } else {
                sPrefsEditor!!.putBoolean(key, value)
            }
            return sPrefsEditor
        }

        fun put(key: String?, value: Float?): SharedPreferences.Editor? {
            if (value == null) {
                sPrefsEditor!!.remove(key)
            } else {
                sPrefsEditor!!.putFloat(key, value)
            }
            return sPrefsEditor
        }

        fun put(key: String?, value: Int?): SharedPreferences.Editor? {
            if (value == null) {
                sPrefsEditor!!.remove(key)
            } else {
                sPrefsEditor!!.putInt(key, value)
            }
            return sPrefsEditor
        }

        fun put(key: String?, value: Long?): SharedPreferences.Editor? {
            if (value == null) {
                sPrefsEditor!!.remove(key)
            } else {
                sPrefsEditor!!.putLong(key, value)
            }
            return sPrefsEditor
        }

        fun put(key: String?, value: String?): SharedPreferences.Editor? {
            if (TextUtils.isEmpty(value)) {
                sPrefsEditor!!.remove(key)
            } else {
                sPrefsEditor!!.putString(key, value)
            }
            return sPrefsEditor
        }

        fun addToSet(key: String?, vararg values: String?): SharedPreferences.Editor? {
            if (values == null || values.size == 0) {
                sPrefsEditor!!.remove(key).apply()
            } else {
                val existing = prefs!!.getStringSet(key, HashSet(values.size))
                existing!!.addAll(Arrays.asList(*values))
                sPrefsEditor!!.putStringSet(key, existing).apply()
            }
            return sPrefsEditor
        }

        fun addToSet(key: String?, values: Set<String>?): SharedPreferences.Editor? {
            if (values == null || values.size == 0) {
                sPrefsEditor!!.remove(key).apply()
            } else {
                val existing = prefs!!.getStringSet(key, HashSet(values.size))
                existing!!.addAll(values)
                sPrefsEditor!!.putStringSet(key, existing).apply()
            }
            return sPrefsEditor
        }

        fun removeFromSet(key: String?, value: String): SharedPreferences.Editor? {
            val existing = prefs!!.getStringSet(key, null)
            if (existing != null) {
                existing.remove(value)
                if (existing.size == 0) {
                    sPrefsEditor!!.remove(key).apply()
                } else {
                    sPrefsEditor!!.putStringSet(key, existing).apply()
                }
            }
            return sPrefsEditor
        }

        fun clear() {
            sPrefsEditor!!.clear().commit()
        }
    }
}