package com.sport.app.feature_event.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sport.app.feature_event.data.cache.entity.MatchEntity
import com.sport.app.feature_event.data.cache.entity.TeamEntity
import com.sport.app.feature_event.data.cache.entity.TeamMatchesEntity
import com.sport.app.utils.Converters

@Database(
    entities = [TeamEntity::class, MatchEntity::class, TeamMatchesEntity::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class SportAppDatabase: RoomDatabase() {
    abstract val dao: SportAppDao
}