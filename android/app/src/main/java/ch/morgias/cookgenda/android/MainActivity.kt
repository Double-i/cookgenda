package ch.morgias.cookgenda.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ch.morgias.cookgenda.android.ui.theme.AndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheme {
                ScaffoldBase()
            }
        }
    }
}

@Composable
@Preview
fun ScaffoldBase() {
    val navController: NavHostController = rememberNavController()
    Scaffold(bottomBar = {
        BottomMenu(navController)
    }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it).background(MaterialTheme.colorScheme.background)
        ) {
            GraphRouter(navController)
        }
    }
}

@Composable
fun BottomMenuItem(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier,
    onClickHandler: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClickHandler() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavigationBarDefaults
        Icon(
            icon,
            contentDescription = text,
            tint = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.secondaryContainer
        )
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun BottomMenu(
    navController: NavHostController,
) {
    var selected by remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    Row(
        modifier = Modifier
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        BottomMenuItem(
            "Recettes",
            Icons.Outlined.Menu,
            selected == Screen.RecipesListResumesScreen.route,

            Modifier
                .weight(1F)
                .fillMaxHeight()
        ) {
            if (selected == Screen.RecipesListResumesScreen.route) {
                return@BottomMenuItem
            }
            selected = Screen.RecipesListResumesScreen.route
            navController.navigate(Screen.RecipesListResumesScreen.route)
        }
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(1.dp)
        )
        BottomMenuItem(
            "Planification",
            Icons.Outlined.DateRange,
            selected == Screen.PlanningListScreen.route,
            Modifier
                .weight(1F)
                .fillMaxHeight()
        ) {
            if (selected == Screen.PlanningListScreen.route) {
                return@BottomMenuItem
            }
            selected = Screen.PlanningListScreen.route
            navController.navigate(Screen.PlanningListScreen.route)
        }
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        BottomMenuItem(
            "Courses",
            Icons.Outlined.ShoppingCart,
            selected == Screen.ShoppingListResumesScreen.route,
            Modifier
                .weight(1F)
                .fillMaxHeight()
        ) {
            if (selected == Screen.ShoppingListResumesScreen.route) {
                return@BottomMenuItem
            }
            selected = Screen.ShoppingListResumesScreen.route
            navController.navigate(Screen.ShoppingListResumesScreen.route)
        }
    }
}