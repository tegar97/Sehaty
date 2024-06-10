package com.miftah.sehaty.di

import com.miftah.sehaty.data.preference.UserPreferenceImpl
import com.miftah.sehaty.domain.preference.UserPreference
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserPreferenceModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManger(userPreferenceImpl : UserPreferenceImpl) : UserPreference
}