package tn.esprit.gamerapp.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.data.News

@Composable
fun NewsCard(modifier: Modifier, news: News){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier.fillMaxWidth(1f)
    ){
        Image(news.image,"", modifier = Modifier.fillMaxWidth(1f),contentScale = ContentScale.Crop)
        Column (modifier = modifier.padding(15.dp)){
            Text(news.title, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Spacer(modifier.height(10.dp))
            Text(news.description, fontSize = 15.sp)
        }
        Row (modifier = modifier.padding(15.dp).fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Text("Show More", fontWeight = FontWeight.Bold, color = Color.Red, fontSize = 16.sp)
        }
    }
}

@Preview
@Composable
fun PreviewNewsCard(){
    NewsCard(Modifier,News(painterResource(R.drawable.sp),"title","description"))
}