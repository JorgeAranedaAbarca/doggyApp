package cl.jaa.doggyapp

import android.app.Application
import cl.jaa.doggyapp.di.appModule
import cl.jaa.doggyapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DoggyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DoggyApp)
            modules(networkModule)
            modules(appModule)
        }
    }
}