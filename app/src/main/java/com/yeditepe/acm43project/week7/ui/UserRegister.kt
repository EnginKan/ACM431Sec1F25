package com.yeditepe.acm43project.week7.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yeditepe.acm43project.ui.theme.ACM43ProjectTheme

import com.yeditepe.acm43project.week7.viewmodel.UserRegisterViewModel
import java.text.SimpleDateFormat
import java.util.Date

enum class Destination(val route: String){
    RegisterOne("registerOne"),
    RegisterTwo("registerTwo")
}

@Composable
fun RegisterScreenOne(viewModel: UserRegisterViewModel,
                      controller: NavController){
       val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
            .padding(top = 25.dp, bottom = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = state.email,
            onValueChange ={
            it->{
                viewModel.updateEmal(it)
                state.email=it
            }
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = state.password, onValueChange ={
            it->{viewModel.updatePassword(it.toString())
            state.password=it}
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = state.confirmPassword, onValueChange ={
                it->{viewModel.updateConfirmPassword(it.toString())
                state.confirmPassword=it
                }
        })
        Row(modifier = Modifier.fillMaxWidth()
        , horizontalArrangement = Arrangement.Start){
            Button(onClick = {controller.navigate(Destination.RegisterTwo.route)},
//                enabled = state.email.isNotEmpty() &&
//                    state.password==state.confirmPassword
                    ) {
                Text(text="Next")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenTwo(viewModel: UserRegisterViewModel,
                      controller: NavController){
    val state by viewModel.uiState.collectAsState()


        var expanded by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
            .padding(top = 25.dp, bottom = 25.dp),
    ) {

    DropdownMenu(expanded = expanded,
        onDismissRequest = {expanded=!expanded}) {

        DropdownMenuItem(
        text ={Text (text="Male")},
            onClick = {viewModel.updateGender("Male")}
            )
        DropdownMenuItem(
            text ={Text (text="Female")},
            onClick = {viewModel.updateGender("Female")}
        )
        }
        Spacer(modifier = Modifier.height(10.dp))
        DatePicker(state = datePickerState)
        LaunchedEffect(datePickerState.selectedDateMillis) {
            datePickerState.selectedDateMillis?.let {
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                val date = Date(it)
                viewModel.updateDateOfBirth(formatter.format(date))
            }

        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = {controller.navigate(Destination.RegisterOne.route)}
            ) {
                Text(text="Previous")
                   }
        }
    }
}

@Composable
fun MainScreen(viewModel: UserRegisterViewModel) {
    val controller = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = Destination.RegisterTwo.route
    ) {
        composable(Destination.RegisterOne.route) {
            RegisterScreenOne(viewModel, controller)
        }
        composable(Destination.RegisterTwo.route) {
            RegisterScreenTwo(viewModel, controller)
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterPreview(){
    ACM43ProjectTheme {
        MainScreen(viewModel = viewModel())
    }
    }

