package andrelsmoraes.newsapp.core.domain.interactor

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<Result> {
    fun execute(vararg params: Pair<String, Any>): Flow<Result>
}
