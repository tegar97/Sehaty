package com.miftah.sehaty.domain.usecase.app_entry

import com.miftah.sehaty.domain.preference.UserPreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveEntry @Inject constructor(
    private val preference: UserPreference
) {
    suspend operator fun invoke(jwt: String) {
        preference.saveJwt(jwt)
    }
}