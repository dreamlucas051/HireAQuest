package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.Quest
import com.example.data.QuestRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuestViewModel(private val repository: QuestRepository) : ViewModel() {
    val allQuests: StateFlow<List<Quest>> = repository.allQuests
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addQuest(quest: Quest) {
        viewModelScope.launch {
            repository.insert(quest)
        }
    }
    
    fun updateQuest(quest: Quest) {
        viewModelScope.launch {
            repository.update(quest)
        }
    }
    
    fun deleteQuest(id: Int) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }
}

class QuestViewModelFactory(private val repository: QuestRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
