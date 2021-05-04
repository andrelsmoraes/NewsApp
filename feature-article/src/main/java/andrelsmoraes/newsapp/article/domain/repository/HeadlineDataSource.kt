package andrelsmoraes.newsapp.article.domain.repository

import andrelsmoraes.newsapp.article.data.network.NewsService
import andrelsmoraes.newsapp.article.domain.contract.IArticleMapper
import andrelsmoraes.newsapp.article.domain.model.Article
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class HeadlineDataSource(
    private val language: String,
    private val newsService: NewsService,
    private val articleMapper: IArticleMapper
) : PagingSource<Int, Article>() {
    private companion object {
        const val INITIAL_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: INITIAL_PAGE
        return try {
            val response = newsService.getHeadlines(language, params.loadSize, page)
            LoadResult.Page(
                data = articleMapper.mapToArticleResponse(response).articles,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.totalResults > 0) page + 1 else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}
