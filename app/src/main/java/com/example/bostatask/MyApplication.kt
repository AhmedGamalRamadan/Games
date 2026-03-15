package com.example.bostatask

import android.app.Application
import com.example.bostatask.di.appModule
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, dataModule, domainModule)
        }
    }
}
