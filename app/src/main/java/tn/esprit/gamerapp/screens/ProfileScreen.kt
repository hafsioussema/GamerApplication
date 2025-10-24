package tn.esprit.gamerapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.data.Game
import tn.esprit.gamerapp.elements.ButtonGamer
import tn.esprit.gamerapp.elements.GameItem
import tn.esprit.gamerapp.elements.TopBar
import tn.esprit.gamerapp.enums.Platform

@Composable
fun ProfileScreen(modifier: Modifier, navCon: NavHostController) {
    val gameArray = arrayOf(
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa)),
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa)),
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa)),
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa))
    )
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column {

            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = modifier.height(10.dp))
                    Image(painterResource(R.drawable.profile), "")
                    Spacer(modifier = modifier.height(20.dp))
                    Text("Fullname", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = modifier.height(10.dp))
                    Text("Email", fontSize = 20.sp)
                    ButtonGamer(modifier, {}, "Edit Profile")
                    Spacer(modifier = modifier.height(10.dp))
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        "Bookmarks",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = modifier.fillMaxWidth()
                    )
                    Spacer(modifier = modifier.height(10.dp))
                }
                items(gameArray) {
                    GameItem(modifier, it, true)
                    Spacer(modifier.height(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(Modifier, rememberNavController())
}