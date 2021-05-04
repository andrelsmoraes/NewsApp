package andrelsmoraes.newsapp.article.domain.interactor

import andrelsmoraes.newsapp.core.domain.repository.ILocaleRepository
import andrelsmoraes.newsapp.article.data.network.NewsService
import andrelsmoraes.newsapp.article.data.network.NewsServiceConfiguration
import andrelsmoraes.newsapp.article.domain.contract.IArticleMapper
import andrelsmoraes.newsapp.article.domain.contract.IGetHeadlinesUseCase
import andrelsmoraes.newsapp.article.domain.model.Article
import andrelsmoraes.newsapp.article.domain.repository.HeadlineDataSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

class GetHeadlinesUseCase(
    private val localeRepository: ILocaleRepository,
    private val newsService: NewsService,
    private val articleMapper: IArticleMapper
) : IGetHeadlinesUseCase {
    @FlowPreview
    override fun execute(vararg params: Pair<String, Any>): Flow<PagingData<Article>> {
        return localeRepository.getDefault()
            .flatMapConcat { locale ->
                Pager(
                    config = PagingConfig(
                        enablePlaceholders = false,
                        pageSize = NewsServiceConfiguration.PAGE_SIZE_DEFAULT
                    ),
                    pagingSourceFactory = {
                        HeadlineDataSource(
                            locale.language,
                            newsService,
                            articleMapper
                        )
                    }
                ).flow
            }
    }
}
