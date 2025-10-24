package tn.esprit.gamerapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.data.News
import tn.esprit.gamerapp.elements.NewsCard
import tn.esprit.gamerapp.elements.TopBar

@Composable
fun NewsScreen(modifier: Modifier, navCon: NavHostController) {
    val newsArray = arrayOf(
        News(
            painterResource(R.drawable.sp),
            "Spider-Man Game",
            "Many news about the game spiderman etcbbydebhe  zedbhzh ehddd"
        ),
        News(
            painterResource(R.drawable.sp),
            "Spider-Man Game",
            "Many news about the game spiderman etcbbydebhe  zedbhzh ehddd"
        ),
        News(
            painterResource(R.drawable.sp),
            "Spider-Man Game",
            "Many news about the game spiderman etcbbydebhe  zedbhzh ehddd"
        ),
        News(
            painterResource(R.drawable.sp),
            "Spider-Man Game",
            "Many news about the game spiderman etcbbydebhe  zedbhzh ehddd"
        )
    )
    Scaffold(
        modifier = modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(newsArray) {
                NewsCard(modifier, it)
                Spacer(modifier.height(10.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsScreen() {
    NewsScreen(Modifier, rememberNavController())
}