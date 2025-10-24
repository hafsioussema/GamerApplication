package tn.esprit.gamerapp.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.data.Game
import tn.esprit.gamerapp.enums.Platform

@Composable
fun GameItem(modifier: Modifier, game: Game,isBookmark: Boolean = false) {
    val icon = if (isBookmark) Icons.Default.BookmarkAdd else Icons.Default.AddShoppingCart
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(game.image, "", modifier.size(100.dp,150.dp), contentScale = ContentScale.FillBounds)
            Spacer(modifier.width(10.dp))
            Column (modifier = modifier){
                Spacer(modifier.height(15.dp))
                Text(game.title, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.Red)
                Spacer(modifier.height(15.dp))
                Text(game.platform.value, fontSize = 20.sp)
                Spacer(modifier.height(15.dp))
                Text(game.price.toString() + " $", fontSize = 20.sp)
            }
            Spacer(modifier.width(10.dp))
            Row (modifier=modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End){
                Column {
                    Spacer(modifier.height(100.dp))
                    Icon(icon, "", tint = Color.Red)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGameIem() {
    GameItem(Modifier, Game("Spider-man", Platform.PS4, 20.50, painterResource(R.drawable.spa)))
}