package com.example.alyss.palindrome

import android.app.Application
import com.example.alyss.palindrome.di.Components.AppComponent
import com.example.alyss.palindrome.di.Components.DaggerAppComponent
import com.example.alyss.palindrome.di.modules.AppModule
import io.realm.Realm

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        component.inject(this)
    }

}