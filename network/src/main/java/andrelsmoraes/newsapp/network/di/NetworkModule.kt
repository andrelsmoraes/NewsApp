package andrelsmoraes.newsapp.network.di

import andrelsmoraes.newsapp.network.BuildConfig
import andrelsmoraes.newsapp.network.NetworkConfiguration
import andrelsmoraes.newsapp.network.RetrofitService
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(NetworkConfiguration.loggingInterceptor)
                }
            }
            .cache(NetworkConfiguration.cache(get()))
            .addInterceptor(NetworkConfiguration.cacheInterceptor)
            .addInterceptor(NetworkConfiguration.authorizationKeyInterceptor)
            .build()
    }

    single {
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(NetworkConfiguration.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
            .build()
    }

    single { RetrofitService(get()) }
}
