package com.miftah.sehaty.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.miftah.sehaty.data.preference.model.UserModel
import com.miftah.sehaty.domain.preference.UserPreference
import com.miftah.sehaty.utils.Constant.IS_LOGIN_KEY
import com.miftah.sehaty.utils.Constant.JWT_KEY
import com.miftah.sehaty.utils.Constant.SHARED_PREFERENCES_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SHARED_PREFERENCES_KEY)

class UserPreferenceImpl @Inject constructor(val dataStore: DataStore<Preferences>) :
    UserPreference {

    override suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[JWT_KEY] = user.jwt
            preferences[IS_LOGIN_KEY] = user.isLogin
        }
    }

    override fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[JWT_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    override suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}