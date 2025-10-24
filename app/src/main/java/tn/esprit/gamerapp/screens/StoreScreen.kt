package tn.esprit.gamerapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.data.Game
import tn.esprit.gamerapp.elements.GameItem
import tn.esprit.gamerapp.elements.TopBar
import tn.esprit.gamerapp.enums.Platform

@Composable
fun StoreScreen(modifier: Modifier,navCon: NavHostController) {
    val gameArray = arrayOf(
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa)),
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa)),
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa)),
        Game("Spider Man", Platform.PS4, 20.8, painterResource(R.drawable.spa))
    )
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(gameArray) {
                GameItem(modifier, it)
                Spacer(modifier.height(10.dp))
            }
        }
    }
}

@Preview
@Composable
fun StoreScreenPreview() {
    StoreScreen(Modifier, rememberNavController())
}