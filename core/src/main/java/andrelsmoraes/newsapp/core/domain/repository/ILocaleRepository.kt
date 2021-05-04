package andrelsmoraes.newsapp.core.domain.repository

import kotlinx.coroutines.flow.Flow
import java.util.Locale

interface ILocaleRepository {
    fun getDefault(): Flow<Locale>
}
