package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {
    @Query("SELECT * FROM quests ORDER BY timestamp DESC")
    fun getAllQuests(): Flow<List<Quest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuest(quest: Quest)

    @Update
    suspend fun updateQuest(quest: Quest)

    @Query("DELETE FROM quests WHERE id = :id")
    suspend fun deleteQuestById(id: Int)
}
