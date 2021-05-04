package andrelsmoraes.newsapp.core.domain.repository

import android.content.Context
import androidx.core.os.ConfigurationCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class LocaleRepository(
    private val context: Context,
) : ILocaleRepository {
    override fun getDefault(): Flow<Locale> {
        return flow {
            try {
                emit(ConfigurationCompat.getLocales(context.resources.configuration)[0])
            } catch (e: Exception) {
                emit(Locale.US)
            }
        }
    }
}
