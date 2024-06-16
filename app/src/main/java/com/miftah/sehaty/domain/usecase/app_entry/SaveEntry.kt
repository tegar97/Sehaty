package com.miftah.sehaty.domain.usecase.app_entry

import com.miftah.sehaty.domain.preference.UserPreference
import javax.inject.Inject

class SaveEntry @Inject constructor(
    private val preference : UserPreference
) {

}