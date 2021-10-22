package com.redville.mealapp.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    /*private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS Category(idCategory INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT NOT NULL, urlThumb TEXT NOT NULL, description TEXT NOT NULL)")
        }
    }*/

    @Provides
    @Singleton
    fun provideMealDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MealDb::class.java, "mealsDb").addMigrations(
            //MIGRATION_1_2
        ).build()

}