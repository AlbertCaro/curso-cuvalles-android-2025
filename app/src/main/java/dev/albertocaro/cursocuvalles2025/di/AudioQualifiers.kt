package dev.albertocaro.cursocuvalles2025.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CreateSound

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DeleteSound

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EditSound