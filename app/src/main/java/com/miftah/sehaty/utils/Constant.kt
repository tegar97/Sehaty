package com.miftah.sehaty.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Constant {
    const val BASE_URL = "https://sehaty.akutegar.com/"

    const val APP_DATABASE = "APP_DATABASE"

    const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"

    const val ITEM_ID = "ITEM_ID"

    val JWT_KEY = stringPreferencesKey("jwt")
    val APP_ENTRY = booleanPreferencesKey("AppEntry")

}