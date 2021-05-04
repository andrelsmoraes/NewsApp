package andrelsmoraes.newsapp.article.domain.contract

import andrelsmoraes.newsapp.core.domain.interactor.BaseUseCase
import andrelsmoraes.newsapp.article.domain.model.Article
import androidx.paging.PagingData

interface IGetHeadlinesUseCase : BaseUseCase<PagingData<Article>>
