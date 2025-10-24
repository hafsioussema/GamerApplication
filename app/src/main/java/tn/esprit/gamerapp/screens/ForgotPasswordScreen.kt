package tn.esprit.gamerapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
import tn.esprit.gamerapp.elements.NavigateBackBar
import tn.esprit.gamerapp.elements.TextFieldGamer
import tn.esprit.gamerapp.enums.TextFieldType
import tn.esprit.gamerapp.ui.theme.GamerAppTheme


@Composable
fun ForgotPasswordScreen(navCon: NavHostController, modifier: Modifier) {
    val rememberEmail = remember { mutableStateOf("") }
    val isErrorEmail = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
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
        topBar = {
            NavigateBackBar{navCon.navigate("login")}
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),

            ) {
            Text(
                "Forgot Password",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(modifier.weight(0.2f))
            Text(
                "Please enter you registered email to reset your password ",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier.weight(0.2f))

            TextFieldGamer(modifier,rememberEmail,isErrorEmail, TextFieldType.EMAIL)
            Spacer(modifier.weight(0.2f))
            ButtonGamer(modifier,{ submitForgotPassword(navCon, snackbarHostState, scope, rememberEmail.value) },"Submit")
            Spacer(modifier.weight(0.2f))
            Text(
                "OR",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier.weight(0.2f))
            ButtonGamer(modifier,{ sendSMS(navCon) },"Send SMS")
            Spacer(modifier.weight(1f))
        }
    }
}

fun submitForgotPassword(
    navCon: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    email: String
) {
    if (email.isEmpty())
        showSnackbar(snackbarHostState, scope, "Please fill all fields")
    else {
        val otp = "1234"
        navCon.navigate("OTPValidation/$otp")
    }
}

fun sendSMS(navCon: NavHostController) {
    val otp = "6789"
    navCon.navigate("OTPValidation/$otp")
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    GamerAppTheme {
        ForgotPasswordScreen(rememberNavController(), Modifier)
    }
}