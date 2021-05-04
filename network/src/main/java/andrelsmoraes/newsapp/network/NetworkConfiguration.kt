package andrelsmoraes.newsapp.network

import andrelsmoraes.newsapp.network.interceptor.AuthorizationKeyInterceptor
import andrelsmoraes.newsapp.network.interceptor.CacheInterceptor
import android.content.Context
import coil.util.CoilUtils
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

internal object NetworkConfiguration {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val AUTHENTICATION_KEY = "add_your_key" //FIXME add key

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val cacheInterceptor = CacheInterceptor()

    val authorizationKeyInterceptor = AuthorizationKeyInterceptor()

    fun cache(context: Context) = CoilUtils.createDefaultCache(context)
}
