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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
fun ResetPasswordScreen(navCon: NavHostController, modifier: Modifier) {
    val rememberPassword = remember { mutableStateOf("") }
    val rememberConfirmPassword = remember { mutableStateOf("") }
    val isErrorPassword = remember { mutableStateOf(false) }
    val isErrorConfirmPassword = remember { mutableStateOf(false) }
    val passwordMismatch = remember { mutableStateOf(false) }
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
        topBar = {NavigateBackBar{navCon.navigate("forgotPassword")}}
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),

            ) {
            Text(
                "Reset Password",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(modifier.weight(0.1f))
            Text(
                "Please enter you new password and confirm it ",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier.weight(0.2f))

            TextFieldGamer(modifier,rememberPassword,isErrorPassword, TextFieldType.PASSWORD,passwordMismatch,rememberConfirmPassword)
            Spacer(modifier.weight(0.2f))
            TextFieldGamer(modifier,rememberConfirmPassword,isErrorConfirmPassword, TextFieldType.CONFIRM_PASSWORD,passwordMismatch,rememberPassword)
            Spacer(modifier.weight(0.2f))

            ButtonGamer(modifier,{submitResetPassword(navCon)},"Submit")


            Spacer(modifier.weight(1f))
        }
    }
}

fun submitResetPassword(navCon: NavHostController){
    navCon.navigate("login")
}


@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    GamerAppTheme {
        ResetPasswordScreen(rememberNavController(), Modifier)
    }
}