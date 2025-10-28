package com.yeditepe.acm43project.ui.myscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import com.yeditepe.acm43project.R
import com.yeditepe.acm43project.ui.theme.ACM43ProjectTheme


@Composable
fun PostCard(){

    Card(
        modifier = Modifier.fillMaxWidth().padding(start=15.dp,end=15.dp)
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "",
                modifier = Modifier.clip(CircleShape)
                    .border(5.dp,
                        MaterialTheme.colorScheme.secondaryContainer).size(40.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("username",
                    style = MaterialTheme.typography.bodyMedium)
                Text(text ="location", style = MaterialTheme.typography.displayMedium)
            }

        }
        Image(painter = painterResource(R.drawable.example), contentDescription = "",
            modifier = Modifier.size(250.dp))
        Row{
            Icon(painter = painterResource(R.drawable.like_icon)
            , contentDescription = "")
            Icon(painter = painterResource(R.drawable.dislike_icon)
                , contentDescription = "")
            Column {
                Text("likes:")
                Text("View all comments")
            }
        }
    }
}
@Composable
fun MainAppScreen(){
    Scaffold(
        bottomBar ={
            BottomAppBar(actions = {
                Icon(painter = painterResource(R.drawable.outline_add_home_24
                ),
                    contentDescription = "")
                Icon(painter = painterResource(R.drawable.like_icon),
                    contentDescription = "")
                Icon(painter = painterResource(R.drawable.like_icon),
                    contentDescription = "")
            })
        }
    ) {
        padding->Column(modifier = Modifier.padding(padding)) {

            PostCard()
    }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PostCardPreview(){
    ACM43ProjectTheme {
        Surface {
            MainAppScreen()
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun PostCardDarkPreview(){
//    PostCard()
//}