package andrelsmoraes.newsapp.article.domain.contract

import andrelsmoraes.newsapp.article.domain.model.ArticleResponse
import kotlinx.coroutines.flow.Flow

interface IHeadlineRepository {
    fun getHeadlines(language: String): Flow<ArticleResponse>
}
