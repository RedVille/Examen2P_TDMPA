package com.redville.mealapp.core.di

import com.redville.mealapp.core.plataform.NetworkHandler
import com.redville.mealapp.data.api.MealApi
import com.redville.mealapp.data.source.CategoryRepositoryImpl
import com.redville.mealapp.data.source.LikeRepositoryImpl
import com.redville.mealapp.data.source.MealRepositoryImpl
import com.redville.mealapp.data.source.UserRepositoryImpl
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.repository.CategoryRepository
import com.redville.mealapp.domain.repository.LikeRepository
import com.redville.mealapp.domain.repository.MealRepository
import com.redville.mealapp.domain.repository.UserRepository
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
    fun provideCategoryRepository(
        apiProvider: ApiProvider,
        mealDb: MealDb,
        networkHandler: NetworkHandler
    ): CategoryRepository =
        CategoryRepositoryImpl(apiProvider.getEndpoint(MealApi::class.java), networkHandler = networkHandler, mealDao = mealDb.mealDao())

    @Provides
    @Singleton
    fun provideMealsRepository(
        apiProvider: ApiProvider,
        mealDb: MealDb,
        networkHandler: NetworkHandler
    ): MealRepository =
        MealRepositoryImpl(apiProvider.getEndpoint(MealApi::class.java), networkHandler = networkHandler, mealDao = mealDb.mealDao())

    @Provides
    @Singleton
    fun provideUserRepository(
        mealDb: MealDb
    ): UserRepository = UserRepositoryImpl(mealDao = mealDb.mealDao())

    @Provides
    @Singleton
    fun provideLikeRepository(
        mealDb: MealDb
    ): LikeRepository = LikeRepositoryImpl(mealDao = mealDb.mealDao())

}