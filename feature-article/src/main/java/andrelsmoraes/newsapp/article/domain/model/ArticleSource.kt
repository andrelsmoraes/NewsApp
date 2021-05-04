package andrelsmoraes.newsapp.article.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleSource(
    val id: String?,
    val title: String?
) : Parcelable
