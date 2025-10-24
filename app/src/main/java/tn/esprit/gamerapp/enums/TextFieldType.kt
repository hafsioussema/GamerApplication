package tn.esprit.gamerapp.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

enum class TextFieldType(
    val keyboardType: KeyboardType,
    val isPassword: Boolean,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    PROFILE(KeyboardType.Text,false,"Profile",Icons.Filled.AccountCircle,"Profile"),
    EMAIL(KeyboardType.Email,false,"Email",Icons.Filled.Email,"Email"),
    PASSWORD(KeyboardType.Password,true,"Password",Icons.Filled.Lock,"Password"),
    CONFIRM_PASSWORD(KeyboardType.Password,true,"Confirm Password",Icons.Filled.Lock,"Confirm Password")

}