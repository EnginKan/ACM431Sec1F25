package com.yeditepe.acm43project.week11.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeditepe.acm43project.week11.database.WordDatabase
import com.yeditepe.acm43project.week11.database.WordEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
enum class UIState {
    LOADING,
    SUCCESS,
    ERROR
}

class WordsViewModel(context: Context) : ViewModel()  {

    private val wordDAO = WordDatabase.getInstance(context).wordDAO()

    private var _word = MutableStateFlow<List<WordEntity>>(emptyList())

    val word = _word.asStateFlow()
    private var _uiState = MutableStateFlow(UIState.LOADING)
    val uiState = _uiState.asStateFlow()
    init{
        getAllWords()
    }
     private fun getAllWords() {
        viewModelScope.launch {
            _word.value = wordDAO.getAllWords()
            _uiState.value = UIState.SUCCESS

        }

    }


}