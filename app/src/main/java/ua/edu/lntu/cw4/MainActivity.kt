package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import ua.edu.lntu.cw4.ui.theme.IPZ_CR_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain()
        }
    }
}

@Composable
fun AppMain() {
    IPZ_CR_4Theme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "first") {
            composable("first") { FirstScreen(navController) }
            composable("second/{selectedItem}", arguments = listOf(navArgument("selectedItem") { type = NavType.IntType })) { backStackEntry ->
                val selectedItem = backStackEntry.arguments?.getInt("selectedItem")
                if (selectedItem != null) {
                    SecondScreen(selectedItem, navController)
                } else {
                    // Handle error case
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "List elements",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
        ItemList(navController)
    }
}

@Composable
fun ItemList(navController: NavController) {
    val items = listOf("First element", "Second element", "Third element")
    Column(Modifier.padding(top = 15.dp)) {
        items.forEachIndexed { index, item ->
            Text(
                text = item,
                modifier = Modifier
                    .clickable { navController.navigate("second/${index + 1}") }
                    .padding(top = 12.dp)
            )
        }
    }
}

@Composable
fun SecondScreen(selectedItem: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "About element",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Number Of Element: $selectedItem")
        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.padding(top = 11.dp)) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IPZ_CR_4Theme {
        AppMain()
    }
}
