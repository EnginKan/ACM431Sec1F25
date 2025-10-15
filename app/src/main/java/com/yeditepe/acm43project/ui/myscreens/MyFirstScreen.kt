package com.yeditepe.acm43project.ui.myscreens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeditepe.acm43project.R
import com.yeditepe.acm43project.ui.theme.ACM43ProjectTheme

@Composable
fun Profile(){

   Row(verticalAlignment = Alignment.CenterVertically) {

       Icon(painter = painterResource(
           id = R.drawable.baseline_person_24),
           contentDescription = "")
       Column(
           modifier = Modifier.padding(
               start = 20.dp, end = 25.dp,
               top = 25.dp
           ).fillMaxWidth()
               .background(
                   color = MaterialTheme.colorScheme.primaryContainer,
                   shape = RoundedCornerShape(corner = CornerSize(5.dp))
               ),
           verticalArrangement = Arrangement.spacedBy(5.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {

           Text(text = "Hello Kotlin!!")
           Text(text = "This is our first composable")
       }
   }
}


@Preview(showBackground = false, showSystemUi = true)
@Composable
fun ProfilePreview(){
    ACM43ProjectTheme {
        Profile()
    }
}