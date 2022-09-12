package com.example.tracingapp.network.di

import com.example.tracingapp.network.api.HistoriesApi
import com.example.tracingapp.network.api.UsersApi
import com.example.tracingapp.data.repository.histories.HistoryRepositoryImpl
import com.example.tracingapp.data.repository.users.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://mycare-05.herokuapp.com/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideHistoryApi(retrofit: Retrofit): HistoriesApi =
        retrofit.create(HistoriesApi::class.java)

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UsersApi =
        retrofit.create(UsersApi::class.java)

    @Singleton
    @Provides
    fun provideUserRepository(api: UsersApi) = UserRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideHistoryRepository(api: HistoriesApi) = HistoryRepositoryImpl(api)
}