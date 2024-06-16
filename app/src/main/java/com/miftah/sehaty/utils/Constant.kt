package com.miftah.sehaty.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Constant {
    const val BASE_URL = "https://sehaty.akutegar.com/"

    const val SIGN_KEY = "79dbd56211226f287600113c533cac9d9162134421b443eb3f2a8333648942a48cb77aac368683dc1ca3c8d5f496db2c69f450ad8a6"

    const val APP_DATABASE = "APP_DATABASE"

    const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"

    val JWT_KEY = stringPreferencesKey("jwt")
    val APP_ENTRY = booleanPreferencesKey("AppEntry")

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return Gson().toJson(list)
    }

}