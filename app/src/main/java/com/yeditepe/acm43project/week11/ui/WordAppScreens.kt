package com.yeditepe.acm43project.week11.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.yeditepe.acm43project.week11.database.WordEntity
import com.yeditepe.acm43project.week11.viewmodels.UIState
import com.yeditepe.acm43project.week11.viewmodels.WordsViewModel


@Composable
fun MainScreen(viewModel: WordsViewModel) {
    //val viewModel: WordsViewModel = viewModel()
    val words by viewModel.word.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        UIState.LOADING -> LoadingScreen()
        UIState.SUCCESS -> WordList(words)
        else -> ErrorScreen()
    }
}
@Composable
fun LoadingScreen() {
    //TODO("Not yet implemented")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Loading...")
    }

}

@Composable
fun WordList(wordList: List<WordEntity>) {
    //TODO("Not yet implemented")
    LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)){
        items(wordList){
            it->Column(Modifier.fillMaxWidth()) {
                Text(text = it.word)
            }
        }

        }
    }


@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error Happens...")
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun MainScreenPreview() {
    MainScreen(viewModel = viewModel())
}



