package com.example.homesecuritytesttask

import android.app.Application
import com.example.homesecuritytesttask.controller.di.appModule
import com.example.homesecuritytesttask.controller.di.dataModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(listOf(appModule, dataModule))
        }
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .name("homeSecurity")
            .build()
        Realm.setDefaultConfiguration(config)
    }
}