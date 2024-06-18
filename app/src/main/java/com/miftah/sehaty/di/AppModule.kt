package com.miftah.sehaty.di

import android.content.Context
import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.miftah.sehaty.core.data.local.room.AppDatabase
import com.miftah.sehaty.core.data.remote.retrofit.ApiHelper
import com.miftah.sehaty.core.data.remote.retrofit.ApiService
import com.miftah.sehaty.core.data.remote.retrofit.ApiServiceImpl
import com.miftah.sehaty.core.repository.AppRepositoryImpl
import com.miftah.sehaty.domain.preference.UserPreference
import com.miftah.sehaty.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        appDatabase: AppDatabase
    ): AppRepositoryImpl =
        AppRepositoryImpl(apiService, appDatabase)

    @Provides
    @Named("URL")
    fun provideBaseUrl(): String =
        Constant.BASE_URL

    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideAuth(userPreference: UserPreference): Interceptor = Interceptor { chain ->
        val req = chain.request()

        val jwt = runBlocking {
            userPreference.getJwt().first()
        }

        val requestHeaders = if (!jwt.isNullOrBlank()) {
            req.newBuilder()
                .addHeader("x-auth-token", jwt)
                .build()
        } else {
            req
        }
        chain.proceed(requestHeaders)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(@Named("URL") url: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun bindApiServiceImpl(apiServiceImpl: ApiServiceImpl): ApiHelper =
        apiServiceImpl

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constant.APP_DATABASE
        ).build()
}