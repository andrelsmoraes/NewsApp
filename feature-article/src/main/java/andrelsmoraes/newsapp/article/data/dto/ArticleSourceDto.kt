package andrelsmoraes.newsapp.article.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleSourceDto(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val title: String?
)
