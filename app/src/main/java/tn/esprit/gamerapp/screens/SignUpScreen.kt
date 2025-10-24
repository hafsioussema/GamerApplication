package tn.esprit.gamerapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import tn.esprit.gamerapp.R
import tn.esprit.gamerapp.elements.ButtonGamer
import tn.esprit.gamerapp.elements.NavigateBackBar
import tn.esprit.gamerapp.elements.TextFieldGamer
import tn.esprit.gamerapp.enums.TextFieldType
import tn.esprit.gamerapp.ui.theme.GamerAppTheme


@Composable
fun SignUpScreen(navCon: NavHostController, modifier: Modifier) {
    val rememberFullname = remember { mutableStateOf("") }
    val rememberEmail = remember { mutableStateOf("") }
    val rememberPassword = remember { mutableStateOf("") }
    val rememberConfirmPassword = remember { mutableStateOf("") }
    val isErrorEmail = remember { mutableStateOf(false) }
    val isErrorPassword = remember { mutableStateOf(false) }
    val isErrorConfirmPassword = remember { mutableStateOf(false) }
    val isErrorFullName = remember { mutableStateOf(false) }
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
        topBar = {NavigateBackBar{navCon.navigate("login")}}
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
            TextFieldGamer(modifier,rememberFullname,isErrorFullName, TextFieldType.PROFILE)
            Spacer(modifier.weight(0.2f))
            TextFieldGamer(modifier,rememberEmail,isErrorEmail, TextFieldType.EMAIL)

            Spacer(modifier.weight(0.2f))
            TextFieldGamer(modifier,rememberPassword,isErrorPassword , TextFieldType.PASSWORD)
            Spacer(modifier.weight(0.2f))
            TextFieldGamer(modifier,rememberConfirmPassword,isErrorConfirmPassword, TextFieldType.CONFIRM_PASSWORD)
            Spacer(modifier.weight(0.2f))

            ButtonGamer(modifier,{
                signUp(
                    navCon,
                    snackbarHostState,
                    scope,
                    rememberFullname.value,
                    rememberEmail.value,
                    rememberPassword.value,
                    rememberConfirmPassword.value
                )
            },"Submit")
            Spacer(modifier.weight(0.2f))
            PrivacyPolicy(modifier,snackbarHostState,scope)
        }
    }
}

@Composable
fun PrivacyPolicy(modifier: Modifier,snackbarHostState: SnackbarHostState, scope: CoroutineScope){
    TextButton(onClick = { showSnackbar(snackbarHostState, scope, "Coming Soon :)") }) {
        Text(
            "By registering you agree to our Terms & Conditions and Privacy Policy ",
            color = Color.Red,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = 30.dp)
        )
    }
}

fun signUp(
    navCon: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    fullname: String,
    email: String,
    password: String,
    confirmPassword: String
) {
    if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullname.isEmpty())
        showSnackbar(snackbarHostState, scope, "Please fill all fields")
    else if (password != confirmPassword)
        showSnackbar(snackbarHostState, scope, "Passwords do not match")
    else
        navCon.navigate("login")
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    GamerAppTheme {
        SignUpScreen(rememberNavController(), Modifier)
    }
}