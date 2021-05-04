package andrelsmoraes.newsapp.core.ui

import androidx.annotation.StringRes

sealed class UiStateEvent {
    object Loading : UiStateEvent()
    class Error(@StringRes val messageRes: Int) : UiStateEvent()
    abstract class Data : UiStateEvent()
}
