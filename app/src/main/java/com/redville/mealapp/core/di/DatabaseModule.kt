package com.redville.mealapp.core.di

import android.content.Context
import androidx.room.Room
import com.redville.mealapp.framework.db.MealDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMealDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MealDb::class.java, "meals").addMigrations(
        ).build()

}