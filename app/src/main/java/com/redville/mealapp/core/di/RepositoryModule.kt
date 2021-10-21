package com.redville.mealapp.core.di

import com.redville.mealapp.core.plataform.NetworkHandler
import com.redville.mealapp.data.api.MealApi
import com.redville.mealapp.data.source.MealRepositoryImpl
import com.redville.mealapp.domain.repository.MealRepository
import com.redville.mealapp.framework.api.ApiProvider
import com.redville.mealapp.framework.db.MealDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealRepository(
        apiProvider: ApiProvider,
        mealDb: MealDb,
        networkHandler: NetworkHandler
    ): MealRepository =
        MealRepositoryImpl(apiProvider.getEndpoint(MealApi::class.java), networkHandler = networkHandler, mealDao = mealDb.mealDao())

}