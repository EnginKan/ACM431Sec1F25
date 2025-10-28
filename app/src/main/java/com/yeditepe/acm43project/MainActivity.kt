package com.yeditepe.acm43project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeditepe.acm43project.ui.theme.ACM43ProjectTheme
import java.util.logging.Logger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACM43ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(

                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("ONSTART","On start state in work")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ONRESUME","On resume state in work")
    }
    override fun onPause() {
        super.onPause()

        Log.println(Log.INFO,"ONPAUSE","On pause state is working")
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text2: MutableState<String?> =
        rememberSaveable { mutableStateOf("") }
    var text:String? by
    rememberSaveable{
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(top=25.dp)
        .fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center){
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    TextField(value = text.toString(),
        onValueChange = {text=it})
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ACM43ProjectTheme {
        Greeting("Android")
    }
}