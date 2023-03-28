package com.example.managetask2.di

import android.app.Application
import androidx.room.Room
import com.example.managetask2.data.database.TaskDatabase
import com.example.managetask2.data.repository_impl.TaskManagerRepositoryImpl
import com.example.managetask2.domain.repository.TaskManagerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTaskManagerDatabase(application: Application): TaskDatabase {
        return Room.databaseBuilder(
            application,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRepository(db: TaskDatabase): TaskManagerRepository{
        return TaskManagerRepositoryImpl(db.dao)
    }
}