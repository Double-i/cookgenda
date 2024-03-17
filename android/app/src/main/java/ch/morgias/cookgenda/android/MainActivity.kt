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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.morgias.cookgenda.android.models.Recipe
import ch.morgias.cookgenda.android.ui.recipesList.RecipesList
import ch.morgias.cookgenda.android.ui.theme.AndroidTheme

val fakeData = listOf<Recipe>(
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
    Recipe("test", "test"),
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheme {
                // A surface container using the 'background' color from the theme
                ScaffoldBase()


            }
        }
    }
}

@Composable
@Preview
fun ScaffoldBase() {
    Scaffold(bottomBar = {
        BottomMenu()
    }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            RecipesList(fakeData)
        }
    }
}
fun coucou(){
    println("COUCOU")
}

@Composable
fun BottomMenuItem(text: String, icon: ImageVector, modifier: Modifier) {
    Column(modifier = modifier.clickable { coucou() }, horizontalAlignment = Alignment.CenterHorizontally) {
        NavigationBarDefaults
        Icon(
            icon, contentDescription = text
        )
        Text(text = text, color = Color.Gray)
    }
}

@Composable
fun BottomMenu() {
    Row(modifier = Modifier
        .height(50.dp)
        .background(Color.Yellow)) {
        BottomMenuItem(
            "Recettes", Icons.Outlined.Menu,
            Modifier
                .weight(1F)
                .fillMaxHeight()
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(1.dp)
        )
        BottomMenuItem(
            "Planification", Icons.Outlined.DateRange,
            Modifier
                .weight(1F)
                .fillMaxHeight()
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(1.dp)
        )
        BottomMenuItem(
            "Courses", Icons.Outlined.ShoppingCart,
            Modifier
                .weight(1F)
                .fillMaxHeight()
        )
    }
}