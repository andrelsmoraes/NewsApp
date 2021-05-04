package andrelsmoraes.newsapp.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

internal class CacheInterceptor : Interceptor {

    private companion object {
        const val HEADER_PRAGMA = "Pragma"
        const val HEADER_CACHE_CONTROL = "Cache-Control"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder()
            .maxStale(1, TimeUnit.HOURS)
            .build()

        return chain.proceed(
            chain.request().newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        )
    }
}
