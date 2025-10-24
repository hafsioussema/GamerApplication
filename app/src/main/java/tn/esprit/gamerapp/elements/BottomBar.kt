package tn.esprit.gamerapp.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBar(navCon: NavController,selectedDestination: MutableState<Int>) {
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets,
        containerColor = MaterialTheme.colorScheme.surface, contentColor = Color.Black) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    navCon.navigate(route = destination.route)
                    selectedDestination.value = index
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = destination.contentDescription,
                        tint = if (selectedDestination.value == index)  Color.White else MaterialTheme.colorScheme.tertiary
                    )
                },
                label = {
                    if (selectedDestination.value == index)
                        Text(destination.label, color = Color.White, fontWeight = FontWeight.Bold)
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(navCon = rememberNavController(), selectedDestination = remember { mutableStateOf(0) })
}