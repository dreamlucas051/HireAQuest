package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quests")
data class Quest(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val location: String,
    val bounty: Double,
    val status: String = "AVAILABLE", // AVAILABLE, ACCEPTED, COMPLETED
    val timestamp: Long = System.currentTimeMillis()
)
