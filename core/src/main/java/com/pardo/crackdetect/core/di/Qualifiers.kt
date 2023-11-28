package com.pardo.crackdetect.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppId

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey
