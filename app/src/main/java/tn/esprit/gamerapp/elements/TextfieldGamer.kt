package tn.esprit.gamerapp.elements

import android.widget.MultiAutoCompleteTextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import tn.esprit.gamerapp.enums.TextFieldType


@Composable
fun TextFieldGamer(
    modifier: Modifier,
    remember: MutableState<String>,
    isError: MutableState<Boolean>,
    props: TextFieldType,
    passwordMismatch: MutableState<Boolean> = remember { mutableStateOf(false) },
    otherPasswordValue: MutableState<String> = remember { mutableStateOf("") }
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = remember.value,
        onValueChange = { text ->
            remember.value = text
            isError.value = text.isEmpty()
            if (props.isPassword && otherPasswordValue.value.isNotEmpty()) {
                passwordMismatch.value = text != otherPasswordValue.value
            }
        },
        isError = isError.value || passwordMismatch.value,
        visualTransformation = if (props.isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        supportingText = {
            if (isError.value) {
                Text("Must Not Be Empty")
            } else {
                if (passwordMismatch.value) {
                    Text("Passwords do not match")
                }
            }
        },
        trailingIcon = {
            when {
                isError.value -> Icon(
                    Icons.Default.Error,
                    contentDescription = "Erreur",
                    tint = Color.Red
                )

                props.isPassword && !isError.value -> IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        tint = Color.Red,
                        imageVector = if (passwordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = if (passwordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        }
                    )
                }
            }
        },
        label = { Text(props.label) },
        leadingIcon = {
            Icon(
                imageVector = props.icon,
                contentDescription = props.contentDescription
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = props.keyboardType)
    )
}

@Preview
@Composable
fun PreviewProfileField() {
    TextFieldGamer(
        Modifier,
        remember { mutableStateOf("") },
        remember { mutableStateOf(false) },
        TextFieldType.PROFILE
    )
}

@Preview
@Composable
fun PreviewEmailField() {
    TextFieldGamer(
        Modifier,
        remember { mutableStateOf("") },
        remember { mutableStateOf(false) },
        TextFieldType.EMAIL
    )
}

@Preview
@Composable
fun PreviewPasswordField() {
    TextFieldGamer(
        Modifier,
        remember { mutableStateOf("") },
        remember { mutableStateOf(false) },
        TextFieldType.PASSWORD
    )
}


