package com.miftah.sehaty.domain.usecase.app_entry

import com.miftah.sehaty.domain.preference.UserPreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckEntry @Inject constructor(
    private val preference: UserPreference
) {
    operator fun invoke(): Flow<Boolean> {
        return preference.readAppEntry()
    }
}