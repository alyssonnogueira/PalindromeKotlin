package com.example.alyss.palindrome.di.modules

import com.example.alyss.palindrome.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}