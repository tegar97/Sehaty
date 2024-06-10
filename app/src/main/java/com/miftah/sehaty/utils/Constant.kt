package com.miftah.sehaty.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constant {
    const val BASE_URL = "http://195.114.14.155:3000"

    const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"
    val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
    val JWT_KEY = stringPreferencesKey("jwt")
}