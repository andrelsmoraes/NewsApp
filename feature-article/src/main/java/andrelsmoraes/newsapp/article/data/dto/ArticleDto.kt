package andrelsmoraes.newsapp.article.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class ArticleDto(
    @Json(name = "source") val source: ArticleSourceDto?,
    @Json(name = "author") val author: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: Date?,
    @Json(name = "content") val content: String?
)
