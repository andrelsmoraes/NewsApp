package andrelsmoraes.newsapp.article.domain.mapper

import andrelsmoraes.newsapp.article.data.dto.ArticleDto
import andrelsmoraes.newsapp.article.data.dto.ArticleResponseDto
import andrelsmoraes.newsapp.article.data.dto.ArticleSourceDto
import andrelsmoraes.newsapp.article.domain.contract.IArticleMapper
import andrelsmoraes.newsapp.article.domain.model.Article
import andrelsmoraes.newsapp.article.domain.model.ArticleResponse
import andrelsmoraes.newsapp.article.domain.model.ArticleSource
import kotlinx.coroutines.coroutineScope

class ArticleMapper : IArticleMapper {
    override suspend fun mapToArticleResponse(
        articleResponseDto: ArticleResponseDto
    ): ArticleResponse = coroutineScope {
        ArticleResponse(
            status = articleResponseDto.status,
            totalResults = articleResponseDto.totalResults,
            articles = articleResponseDto.articles.map { dto -> mapToArticle(dto) }
        )
    }

    override suspend fun mapToArticleSource(
        articleSourceDto: ArticleSourceDto
    ): ArticleSource = coroutineScope {
        ArticleSource(
            id = articleSourceDto.id,
            title = articleSourceDto.title
        )
    }

    override suspend fun mapToArticle(articleDto: ArticleDto): Article = coroutineScope {
        Article(
            source = if (articleDto.source != null) mapToArticleSource(articleDto.source) else null,
            author = articleDto.author,
            title = articleDto.title,
            description = articleDto.description,
            url = articleDto.url,
            urlToImage = articleDto.urlToImage,
            publishedAt = articleDto.publishedAt,
            content = articleDto.content
        )
    }
}
