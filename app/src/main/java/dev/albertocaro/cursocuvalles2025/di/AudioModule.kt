package dev.albertocaro.cursocuvalles2025.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.albertocaro.cursocuvalles2025.R
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AudioModule {

    @Singleton
    @Provides
    @CreateSound
    fun provideCreationAudio(): Int = R.raw.create

    @Singleton
    @Provides
    @DeleteSound
    fun provideDeleteAudio(): Int = R.raw.delete

    @Singleton
    @Provides
    @EditSound
    fun provideEditAudio(): Int = R.raw.edit
}