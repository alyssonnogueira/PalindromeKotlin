package com.example.alyss.palindrome.di.Components

import com.example.alyss.palindrome.App
import com.example.alyss.palindrome.di.modules.AppModule
import com.example.alyss.palindrome.repositories.PalindromeRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(repository: PalindromeRepository)
//    fun plus(mainModule: MainModule): MainComponent
}