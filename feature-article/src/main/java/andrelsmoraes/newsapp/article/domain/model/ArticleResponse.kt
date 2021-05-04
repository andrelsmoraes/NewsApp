package andrelsmoraes.newsapp.article.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Parcelable
