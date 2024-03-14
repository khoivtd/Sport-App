package com.sport.app.feature_event.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sport.app.feature_event.data.cache.entity.MatchEntity
import com.sport.app.feature_event.data.cache.entity.TeamEntity
import com.sport.app.feature_event.data.cache.entity.TeamMatchesEntity

@Dao
interface SportAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamEntities(teams: List<TeamEntity>)

    @Query("SELECT * FROM teamentity")
    suspend fun getTeamEntities(): List<TeamEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchesEntities(matches: List<MatchEntity>)

    @Query("SELECT * FROM matchentity")
    suspend fun getMatchEntities(): List<MatchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamMatchesEntity(teamMatches: TeamMatchesEntity)

    @Query("SELECT * FROM teammatchesentity WHERE id = :id")
    suspend fun getTeamMatchesEntities(id: String): TeamMatchesEntity

}