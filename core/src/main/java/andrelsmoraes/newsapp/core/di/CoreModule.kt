package andrelsmoraes.newsapp.core.di

import andrelsmoraes.newsapp.core.domain.repository.ILocaleRepository
import andrelsmoraes.newsapp.core.domain.repository.LocaleRepository
import org.koin.dsl.module

val coreModule = module {
    single<ILocaleRepository> { LocaleRepository(get()) }
}
