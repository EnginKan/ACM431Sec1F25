package com.yeditepe.acm43project.week6.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yeditepe.acm43project.R
import com.yeditepe.acm43project.week6.viewmodel.GameDataViewModel

// Placeholder for a custom theme that matches the dark, earthy tones in the image
// In a real app, you would define your own MD3 theme (color scheme)
private val DarkBrown = Color(0xFF382B24)
private val LightBrown = Color(0xFF7A6458)
private val LightOrange = Color(0xFFFC9F0F)
private val DarkTheme = androidx.compose.material3.darkColorScheme(
    primary = LightOrange,
    onPrimary = Color.Black,
    surface = DarkBrown,
    onSurface = Color.White,
    background = DarkBrown,
    onBackground = Color.White,
    secondaryContainer = LightBrown,
    onSecondaryContainer = Color.White
)

data class GameData(
    val team1Name: String,
    val team2Name: String,
    val team1Score: Int,
    val team2Score: Int,
    val timeRemaining: String,
    val quarter: String,
    val team1FG: String,
    val team2FG: String,
    val team1Rebounds: Int,
    val team2Rebounds: Int,
    val team1Assists: Int,
    val team2Assists: Int,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketballScoreCardScreen(gameModel: GameDataViewModel) {
    // A Surface with the custom dark background color for the overall screen
    val game = gameModel.gameUIState.collectAsState()
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("NBA", color = MaterialTheme.colorScheme.onBackground) },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle back */ }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            },
            bottomBar = { ScoreCardBottomBar() }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Live Match Info Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        // Placeholder for the team logo/icon
                        Icon(
                            Icons.Default.AccountBox,
                            contentDescription = "Team Logo",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Text(
                            text = "Live",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = LightOrange // Use primary color for "Live"
                        )
                        Text(
                            text = "${game.value.team1Name} vs. ${game.value.team2Name}...",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "7:00 PM",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }

                // 2. Scoreboard
                Text(
                    text = "${game.value.team1Score} - ${game.value.team2Score}",
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row (modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {gameModel.incrementTeamOneScore()}) {
                        Text(text = "+1")
                    }
                    Button(onClick = {gameModel.incrementTeamTwoScore()}) {
                        Text(text = "+1")
                    }

                }
                Text(
                    text = "${game.value.quarter} ${game.value.timeRemaining}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // 3. Stats Section
                StatCard(
                    statLabel = "Field Goal %",
                    team1Value = game.value.team1FG,
                    team2Value = game.value.team2FG,
                    teamNameText = "${game.value.team1Name} vs. ${game.value.team2Name}",
                    logoPlaceholder = "FG"
                )
                Spacer(modifier = Modifier.height(12.dp))
                StatCard(
                    statLabel = "Rebounds",
                    team1Value = game.value.team1Rebounds.toString(),
                    team2Value = game.value.team2Rebounds.toString(),
                    teamNameText = "${game.value.team1Name} vs. ${game.value.team2Name}",
                    logoPlaceholder = "Rebounds"
                )
                Spacer(modifier = Modifier.height(12.dp))
                StatCard(
                    statLabel = "Assists",
                    team1Value = game.value.team1Assists.toString(),
                    team2Value = game.value.team2Assists.toString(),
                    teamNameText = "${game.value.team1Name} vs. ${game.value.team2Score}",
                    logoPlaceholder = "Assists"
                )
            }
        }
    }
}

@Composable
fun StatCard(
    statLabel: String,
    team1Value: String,
    team2Value: String,
    teamNameText: String,
    logoPlaceholder: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkBrown, // Using background color for cards to match screenshot
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        // Minimal elevation to keep it flat, as in the original design
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = statLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
                Text(
                    text = "$team1Value - $team2Value",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = teamNameText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }

            // Placeholder for the small basketball-themed image
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFF261D19), RoundedCornerShape(8.dp)), // A slightly darker brown
                contentAlignment = Alignment.Center
            ) {
                // In a real app, this would be an image
                Text(
                    text = logoPlaceholder,
                    style = MaterialTheme.typography.labelSmall,
                    color = LightOrange
                )
            }
        }
    }
}

@Composable
fun ScoreCardBottomBar() {
    NavigationBar(
        containerColor = Color(0xFF201815), // Darker color for the Navigation Bar
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.example), contentDescription = "Scores") },
            label = { Text("Scores") },
            selected = true, // The 'Scores' tab is active
            onClick = { /* Navigate to Scores */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Build, contentDescription = "Schedule") },
            label = { Text("Schedule") },
            selected = false,
            onClick = { /* Navigate to Schedule */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Build, contentDescription = "Teams") },
            label = { Text("Teams") },
            selected = false,
            onClick = { /* Navigate to Teams */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "News") },
            label = { Text("News") },
            selected = false,
            onClick = { /* Navigate to News */ }
        )
    }
}

// NOTE: For the icons (like the basketball in the bottom bar),
// you would need to replace `painterResource(id = R.drawable.ic_basketball)`
// with an actual vector asset in your Android project.
// For simplicity in this example, I'll use a local drawable resource placeholder
// and include a mock to run the preview.

// --- Mocking for Preview ---
// This is necessary because `painterResource` can't be resolved in the code block.
// In a real project, this part is not needed.
// For the purpose of providing a complete answer, I will comment out the
// one line that requires a local resource.


 @Preview
 @Composable
 fun BasketballScoreCardScreenPreview() {
    MaterialTheme(colorScheme = DarkTheme) {
        BasketballScoreCardScreen(gameModel = viewModel ())
    }
 }
