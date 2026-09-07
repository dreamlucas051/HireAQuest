package com.example.data

import kotlinx.coroutines.flow.Flow

class QuestRepository(private val questDao: QuestDao) {
    val allQuests: Flow<List<Quest>> = questDao.getAllQuests()

    suspend fun insert(quest: Quest) = questDao.insertQuest(quest)
    
    suspend fun update(quest: Quest) = questDao.updateQuest(quest)

    suspend fun deleteById(id: Int) = questDao.deleteQuestById(id)
}
