package andrelsmoraes.newsapp.network.interceptor

import andrelsmoraes.newsapp.network.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization", NetworkConfiguration.AUTHENTICATION_KEY)
            .build())
    }
}
