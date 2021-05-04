package andrelsmoraes.newsapp

import andrelsmoraes.newsapp.core.di.coreModule
import andrelsmoraes.newsapp.article.di.newsModule
import andrelsmoraes.newsapp.network.di.networkModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@NewsAppApplication)
            loadKoinModules(listOf(
                coreModule,
                networkModule,
                newsModule
            ))
        }
    }
}
