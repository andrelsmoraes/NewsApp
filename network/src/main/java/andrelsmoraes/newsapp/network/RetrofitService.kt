package andrelsmoraes.newsapp.network

import retrofit2.Retrofit

class RetrofitService(private val retrofit: Retrofit) {
    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
