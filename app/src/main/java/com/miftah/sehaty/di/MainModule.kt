package com.miftah.sehaty.di

import androidx.viewbinding.BuildConfig
import com.miftah.sehaty.data.remote.retrofit.ApiHelper
import com.miftah.sehaty.data.remote.retrofit.ApiService
import com.miftah.sehaty.data.remote.retrofit.ApiServiceImpl
import com.miftah.sehaty.data.repository.AppRepositoryImpl
import com.miftah.sehaty.domain.preference.UserPreference
import com.miftah.sehaty.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
object MainModule {
    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService
    ): AppRepositoryImpl = AppRepositoryImpl(apiService)

    @Provides
    @Named("URL")
    fun provideBaseUrl(): String = Constant.BASE_URL

    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

    @Provides
    @Singleton
    fun provideAuth(userPreference: UserPreference): Interceptor = Interceptor { chain ->
        val req = chain.request()

        val jwt = runBlocking {
            userPreference.getSession().first().jwt
        }

        val requestHeaders = if (jwt.isNotBlank()) {
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
    fun bindApiServiceImpl(apiServiceImpl: ApiServiceImpl): ApiHelper = apiServiceImpl
}