package com.kathayat.weboconnectassignment.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefDataStore {

    private var sharedPreferences: SharedPreferences? = null

    fun setValue(context: Context, key: String, value: String) {
        checkPreferences(context)
        sharedPreferences?.edit()?.putString(key, value)?.apply()
    }

    fun remove(context: Context, key: String) {
        checkPreferences(context)
        sharedPreferences?.edit()?.remove(key)?.apply()
    }

    fun getValue(context: Context, key: String): String {
        checkPreferences(context)
        return sharedPreferences?.getString(key, "")!!
    }

    fun clear() {
        sharedPreferences?.edit()?.clear()?.apply()
    }

    private fun checkPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_prefrences_weboconnect", Context.MODE_PRIVATE)
    }

    companion object {
        private var dataStore: SharedPrefDataStore? = null


        fun init(): SharedPrefDataStore {
            if (dataStore == null) {
                dataStore = SharedPrefDataStore()
            }
            return dataStore!!
        }
    }
}