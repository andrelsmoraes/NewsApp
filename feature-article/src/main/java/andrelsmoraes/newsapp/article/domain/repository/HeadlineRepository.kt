package andrelsmoraes.newsapp.article.domain.repository

import andrelsmoraes.newsapp.article.data.network.NewsService
import andrelsmoraes.newsapp.article.domain.contract.IArticleMapper
import andrelsmoraes.newsapp.article.domain.contract.IHeadlineRepository
import andrelsmoraes.newsapp.article.domain.model.ArticleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HeadlineRepository(
    private val newsService: NewsService,
    private val articleMapper: IArticleMapper
) : IHeadlineRepository {
    override fun getHeadlines(language: String): Flow<ArticleResponse> {
        return flow {
            emit(articleMapper.mapToArticleResponse(newsService.getHeadlines(language)))
        }.flowOn(Dispatchers.IO)
    }
}
