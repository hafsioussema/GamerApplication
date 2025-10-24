import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Store
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    NEWS("news", "News", Icons.Default.Newspaper, "News"),
    STORE("store", "Store", Icons.Default.Store, "Store"),
    PROFILE("profile", "Profile", Icons.Default.AccountCircle, "Profile")
}