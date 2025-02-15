package dev.albertocaro.cursocuvalles2025.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.albertocaro.cursocuvalles2025.core.RetrofitHelper
import dev.albertocaro.cursocuvalles2025.data.api.service.PostApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitHelper.getRetrofit()

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit): PostApiService = retrofit.create(PostApiService::class.java)
}