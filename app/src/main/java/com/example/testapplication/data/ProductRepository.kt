package com.example.testapplication.data

import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class repository @Inject constructor(
    val dao: dao,
) {
    suspend fun Update(day: ProductLocaleData) {
        dao.Update(day)
    }

    suspend fun Insert(day: ProductLocaleData) {
        dao.Insert(day)
    }
    suspend fun getAllProductsLocale() = dao.getAll()

}


@Module
class RepositoryModule {
    @Provides
    fun provideHealthRepository(dao: dao): repository {
        return repository(dao = dao)
    }
}