package de.amirrocker.flowplayground

import android.app.Application
import de.amirrocker.flowplayground.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FlowPlaygroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FlowPlaygroundApplication)
            if(BuildConfig.DEBUG) androidLogger(Level.ERROR) else Level.NONE
            modules(presentationModule, networkModule, repositoryModule, domainModule, databaseModule)
        }

    }
}