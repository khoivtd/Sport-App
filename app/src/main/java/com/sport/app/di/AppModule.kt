package com.sport.app.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.sport.app.feature_event.data.cache.db.SportAppDatabase
import com.sport.app.feature_event.data.remote.api.SportAppApi
import com.sport.app.feature_event.data.repository.MatchRepositoryImpl
import com.sport.app.feature_event.data.repository.TeamRepositoryImpl
import com.sport.app.feature_event.domain.repository.MatchRepository
import com.sport.app.feature_event.domain.repository.TeamRepository
import com.sport.app.utils.Constants.API_ENDPOINT
import com.sport.app.utils.Constants.DB_APP_NAME
import com.sport.app.utils.Converters
import com.sport.app.utils.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//SingletonComponent makes all the dependencies inside this module live as long as the application does
//and makes sure that we have only a single instance throughout the lifetime of the application
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSportsApi(): SportAppApi {
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSportAppDatabase(app: Application): SportAppDatabase {
        return Room.databaseBuilder(
            app, SportAppDatabase::class.java, DB_APP_NAME
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideTeamRepository(
        api: SportAppApi,
        db: SportAppDatabase
    ): TeamRepository {
        return TeamRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideMatchRepository(
        api: SportAppApi,
        db: SportAppDatabase
    ): MatchRepository {
        return MatchRepositoryImpl(api, db.dao)
    }
}