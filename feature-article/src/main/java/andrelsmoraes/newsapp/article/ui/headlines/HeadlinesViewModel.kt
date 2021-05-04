package andrelsmoraes.newsapp.article.ui.headlines

import andrelsmoraes.newsapp.core.ui.UiStateEvent
import andrelsmoraes.newsapp.article.R
import andrelsmoraes.newsapp.article.domain.contract.IGetHeadlinesUseCase
import andrelsmoraes.newsapp.article.domain.model.Article
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HeadlinesViewModel(
    private val getHeadlinesUseCase: IGetHeadlinesUseCase
) : ViewModel() {
    internal class EverythingData(val data: PagingData<Article>) : UiStateEvent.Data()

    val uiState: MutableLiveData<UiStateEvent> = MutableLiveData(UiStateEvent.Loading)

    init {
        getHeadlines()
    }

    fun getHeadlines() {
        uiState.value = UiStateEvent.Loading
        viewModelScope.launch {
            getHeadlinesUseCase.execute()
                .cachedIn(viewModelScope)
                .catch { throwable ->
                    Timber.e(throwable)
                    uiState.value = UiStateEvent.Error(R.string.error_fetching_headlines)
                }.collect { pagingData ->
                    uiState.value = EverythingData(pagingData)
                }
        }
    }
}
