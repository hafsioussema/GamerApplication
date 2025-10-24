package tn.esprit.gamerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tn.esprit.gamerapp.elements.BottomBar
import tn.esprit.gamerapp.elements.TopBar
import tn.esprit.gamerapp.screens.LoginScreen
import tn.esprit.gamerapp.screens.SignUpScreen
import tn.esprit.gamerapp.screens.SplashScreen
import tn.esprit.gamerapp.screens.ForgotPasswordScreen
import tn.esprit.gamerapp.screens.NewsScreen
import tn.esprit.gamerapp.screens.OTPValidationScreen
import tn.esprit.gamerapp.screens.ProfileScreen
import tn.esprit.gamerapp.screens.ResetPasswordScreen
import tn.esprit.gamerapp.screens.StoreScreen
import tn.esprit.gamerapp.ui.theme.GamerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navCon = rememberNavController()
            val showSplashScreen = remember { mutableStateOf(true) }
            GamerAppTheme {
                if (showSplashScreen.value) {

                    SplashScreen(modifier = Modifier) {
                        showSplashScreen.value = false
                    }
                } else
                    MainScreen(navCon, modifier = Modifier)
            }
        }
    }
}

@Composable
fun MainScreen(navCon: NavHostController, modifier: Modifier = Modifier) {
    val startDestination = Destination.NEWS
    val selectedDestination = remember { mutableStateOf(startDestination.ordinal) }
    val navBackStackEntry by navCon.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        topBar = {
            when (currentRoute) {
                Destination.NEWS.route, Destination.STORE.route, Destination.PROFILE.route -> {
                    TopBar(navCon, modifier, Destination.entries[selectedDestination.value])
                }
                else -> {}
            }
        },
        bottomBar = {
            when (currentRoute) {
                Destination.NEWS.route, Destination.STORE.route, Destination.PROFILE.route -> {
                    BottomBar(navCon, selectedDestination)
                }
                else -> {}
            }
        },
        floatingActionButton = {
            when (currentRoute) {
                Destination.STORE.route -> {
                    FloatingActionButton(onClick = { }, containerColor = Color.Red,contentColor = Color.White) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navCon, startDestination = "login",
            modifier = modifier.padding(innerPadding),
        ) {
            composable("login") { LoginScreen(navCon, modifier) }
            composable("signup") { SignUpScreen(navCon, modifier) }
            composable("forgotPassword") { ForgotPasswordScreen(navCon, modifier) }
            composable ("OTPValidation/{otp}"){  entry ->
                val otp = entry.arguments?.getString("otp")
                OTPValidationScreen(navCon, modifier,otp.toString())
            }
            composable ("resetPassword"){ ResetPasswordScreen(navCon,modifier) }
            composable(Destination.NEWS.route) { NewsScreen(modifier, navCon) }
            composable(Destination.STORE.route) { StoreScreen(modifier, navCon) }
            composable(Destination.PROFILE.route) { ProfileScreen(modifier, navCon) }
        }
    }
}
