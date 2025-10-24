package tn.esprit.gamerapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.input.ImeAction
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
import tn.esprit.gamerapp.ui.theme.GamerAppTheme


@Composable
fun OTPValidationScreen(navCon: NavHostController, modifier: Modifier, otpValue: String) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var otpCode by remember { mutableStateOf("") }
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Enter the code sent to you by email or mobile number",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Spacer(modifier.weight(0.2f))
            OtpInput(
                otpValue = otpCode,
                onOtpChange = { otpCode = it },
                onOtpComplete = { code ->
                    println("Complete: $code")
                }
            )
            Spacer(modifier.weight(0.1f))
            ButtonGamer(modifier, {verifyOtpCode(navCon,snackbarHostState,scope,otpCode,otpValue.toString())}, "Verify")
            Spacer(modifier.weight(0.1f))
            Text(
                "Didn't receive a verification code ?",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(horizontal = 30.dp)
            )
            TextButton(onClick = { showSnackbar(snackbarHostState, scope, "Coming Soon :)") }) {
                Text(
                    "Resend Code",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier.weight(1f))
        }
    }
}

fun verifyOtpCode(
    navCon: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    enteredOtp: String,
    otp: String
) {
    if (enteredOtp == otp)
        navCon.navigate("resetPassword")
    else
        showSnackbar(snackbarHostState, scope, "Wrong Code !")
}

@Composable
fun OtpInput(
    otpValue: String,
    onOtpChange: (String) -> Unit,
    onOtpComplete: (String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BasicTextField(
            value = otpValue,
            onValueChange = { newValue ->
                if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                    onOtpChange(newValue)
                    if (newValue.length == 4) {
                        onOtpComplete(newValue)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(4) { index ->
                        val char = otpValue.getOrNull(index)?.toString() ?: ""
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .border(
                                    width = 2.dp,
                                    color = if (index == otpValue.length) MaterialTheme.colorScheme.primary else Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OTPValidationScreenPreview() {
    GamerAppTheme {
        OTPValidationScreen(rememberNavController(), Modifier, otpValue = "1234")
    }
}