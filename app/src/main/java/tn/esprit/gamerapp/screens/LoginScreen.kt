package tn.esprit.gamerapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.elements.ButtonGamer
import tn.esprit.gamerapp.elements.TextFieldGamer
import tn.esprit.gamerapp.enums.TextFieldType
import tn.esprit.gamerapp.ui.theme.GamerAppTheme


@Composable
fun LoginScreen(navCon: NavHostController, modifier: Modifier) {
    val rememberMeChecked = remember { mutableStateOf(false) }
    val rememberEmail = remember { mutableStateOf("") }
    val rememberPassword = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val isErrorEmail = remember { mutableStateOf(false) }
    val isErrorPassword = remember { mutableStateOf(false) }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color.Red,
                    contentColor = Color.White,
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(R.drawable.logo_gamer), "", Modifier.size(150.dp))
            Spacer(modifier.weight(0.2f))
            TextFieldGamer(modifier,rememberEmail,isErrorEmail, TextFieldType.EMAIL)

            Spacer(modifier.weight(0.2f))
            TextFieldGamer(modifier,rememberPassword,isErrorPassword, TextFieldType.PASSWORD)

            Spacer(modifier.weight(0.2f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        rememberMeChecked.value,
                        { rememberMeChecked.value = !rememberMeChecked.value })
                    Text("Remember Me", color = Color.Red, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier.width(25.dp))
                TextButton(onClick = { navToForgotPassword(navCon) }) {
                    Text("Forgot Password ?", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier.weight(0.2f))
            ButtonGamer(modifier,{login(navCon,snackbarHostState,scope,rememberEmail.value,rememberPassword.value)},"Login")
            Spacer(modifier.weight(0.2f))
            Text("OR", color = Color.Red, fontWeight = FontWeight.Bold)
            Spacer(modifier.weight(0.2f))
            Row {
                Button({ showSnackbar(snackbarHostState, scope, "Coming Soon :)") }) {
                    Text("Facebook", color = Color.White)
                }
                Spacer(modifier.width(100.dp))
                Button({ showSnackbar(snackbarHostState, scope, "Coming Soon :)") }) {
                    Text("Google", color = Color.White)
                }
            }
            Spacer(modifier.weight(0.2f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account? ", color = Color.Red, fontWeight = FontWeight.Bold)
                TextButton(onClick = { navToSignUp(navCon) }) {
                    Text(
                        "Register Now",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

fun showSnackbar(snackbarHostState: SnackbarHostState, scope: CoroutineScope, message: String) {
    scope.launch {
        snackbarHostState.showSnackbar(message)
    }
}

fun navToSignUp(navCon: NavHostController) {
    navCon.navigate("signup")
}

fun navToForgotPassword(navCon: NavHostController) {
    navCon.navigate("forgotPassword")
}

fun login(navCon: NavHostController, snackbarHostState: SnackbarHostState,scope: CoroutineScope,email: String, password: String) {
    if (email.isEmpty() || password.isEmpty())
        showSnackbar(snackbarHostState,scope,"Please fill all fields")
    else
        navCon.navigate(Destination.NEWS.route)
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GamerAppTheme {
        LoginScreen(rememberNavController(), Modifier)
    }
}