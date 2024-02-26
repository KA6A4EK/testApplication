package com.example.testapplication.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.room.Upsert
import dagger.Module
import dagger.Provides


@Dao
interface dao {
    @Query("SELECT * FROM ProductsLocale")
    suspend fun getAll(): List<ProductLocaleData>

    @Insert
    suspend fun Insert(day: ProductLocaleData)

    @Upsert
    suspend fun Update(day: ProductLocaleData)
}

@Database(entities = [ProductLocaleData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val ProductDao: dao
}


@Module
class DaoModule() {
    @Provides
    fun provideDao(database: AppDatabase): dao {
        return database.ProductDao
    }

    @Provides
    fun provideDB(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ProductsDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}