package com.yeditepe.acm43project.ui.myscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeditepe.acm43project.ui.theme.ACM43ProjectTheme

@Composable
fun Counter(){

    var counter =
        rememberSaveable { mutableStateOf(0)
        }
    Column(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .padding(top=20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text="${counter.value}",
            modifier = Modifier.background(color= MaterialTheme.colorScheme.secondaryContainer,
                shape= RectangleShape).size(height = 50.dp, width = 50.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Button(onClick = {counter.value++}){
            Text(text= "Press")
        }
    }
}

@Preview(device = "id:pixel_9_pro", showSystemUi = true, showBackground = true)
@Composable
fun CounterComposable(){
    ACM43ProjectTheme {
        Surface {
            Counter()
        }
    }
}