package ua.edu.lntu.cw4


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.edu.lntu.cw4.ui.theme.IPZ_CR_4Theme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CR_4Theme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "first") {
                    composable("first") { FirstScreen(navController) }
                    composable("second/{selectedItem}") { backStackEntry ->
                        SecondScreen(
                            selectedItem = backStackEntry.arguments?.getInt("selectedItem") ?: -1,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IPZ_CR_4Theme {
        MyApp()
    }
}
