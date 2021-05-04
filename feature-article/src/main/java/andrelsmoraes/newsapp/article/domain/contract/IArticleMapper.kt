package andrelsmoraes.newsapp.article.domain.contract

import andrelsmoraes.newsapp.article.data.dto.ArticleDto
import andrelsmoraes.newsapp.article.data.dto.ArticleResponseDto
import andrelsmoraes.newsapp.article.data.dto.ArticleSourceDto
import andrelsmoraes.newsapp.article.domain.model.Article
import andrelsmoraes.newsapp.article.domain.model.ArticleResponse
import andrelsmoraes.newsapp.article.domain.model.ArticleSource

interface IArticleMapper {
    suspend fun mapToArticleResponse(articleResponseDto: ArticleResponseDto): ArticleResponse
    suspend fun mapToArticleSource(articleSourceDto: ArticleSourceDto): ArticleSource
    suspend fun mapToArticle(articleDto: ArticleDto): Article
}
