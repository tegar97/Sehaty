package com.miftah.sehaty.domain.preference

import com.miftah.sehaty.data.preference.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserPreference {
    suspend fun saveSession(user: UserModel)

    fun getSession(): Flow<UserModel>

    suspend fun logout()
}