package com.miftah.sehaty.di

import com.miftah.sehaty.core.repository.AppRepositoryImpl
import com.miftah.sehaty.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

}
