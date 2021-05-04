package andrelsmoraes.newsapp.article.di

import andrelsmoraes.newsapp.article.data.network.NewsService
import andrelsmoraes.newsapp.article.domain.contract.IArticleMapper
import andrelsmoraes.newsapp.article.domain.contract.IHeadlineRepository
import andrelsmoraes.newsapp.article.domain.contract.IGetHeadlinesUseCase
import andrelsmoraes.newsapp.article.domain.interactor.GetHeadlinesUseCase
import andrelsmoraes.newsapp.article.domain.mapper.ArticleMapper
import andrelsmoraes.newsapp.article.domain.repository.HeadlineRepository
import andrelsmoraes.newsapp.article.ui.headlines.HeadlinesViewModel
import andrelsmoraes.newsapp.network.RetrofitService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    single { get<RetrofitService>().create(NewsService::class.java) }

    single<IArticleMapper> { ArticleMapper() }

    single<IHeadlineRepository> { HeadlineRepository(get(), get()) }

    single<IGetHeadlinesUseCase> { GetHeadlinesUseCase(get(), get(), get()) }

    viewModel { HeadlinesViewModel(get()) }
}
