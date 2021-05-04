package andrelsmoraes.newsapp.article.data.network

import andrelsmoraes.newsapp.article.data.dto.ArticleResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? =  null,
    ): ArticleResponseDto
}
