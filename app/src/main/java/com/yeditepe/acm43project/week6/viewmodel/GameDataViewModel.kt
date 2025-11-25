package com.yeditepe.acm43project.week6.viewmodel

import androidx.lifecycle.ViewModel
import com.yeditepe.acm43project.week6.ui.GameData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

val game = GameData(
team1Name = "Los Angeles Lakers",
team2Name = "Boston Celtics",
team1Score = 102,
team2Score = 98,
timeRemaining = "0:32",
quarter = "Q4",
team1FG = "48%",
team2FG = "45%",
team1Rebounds = 42,
team2Rebounds = 38,
team1Assists = 25,
team2Assists = 22
)

class GameDataViewModel(gameData: GameData = game): ViewModel() {

    private var _gameUIState= MutableStateFlow<GameData>(gameData)

    val gameUIState: StateFlow<GameData> = _gameUIState.asStateFlow()



    fun incrementTeamOneScore(){
        _gameUIState.value.team1Score.inc()
    }
    fun incrementTeamTwoScore(){
        _gameUIState.value.team2Score.inc()
    }

}