package ch.morgias.cookgenda.android.ui.screens.recipesExplorer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.Screen
import ch.morgias.cookgenda.android.models.Recipe

val list = listOf<Recipe>(
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

@Composable
@Preview
fun RecipesList(navController: NavHostController, viewModel: RecipeViewModel) {
    viewModel.RecipeUiState
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(list) { recipe ->
                val shape = RoundedCornerShape(20)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, shape)
                        .background(color = Color.Green)
                        .height(80.dp)
                        .clickable { navController.navigate(Screen.PlanningListScreen.route) }
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.recipe),
                        "Recipe image",
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(15))
                    )
                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(10.dp)
                    ) {
                        Text("COUCOU")
                        Text(color = Color.Gray, text = "COUCOU2")
                    }
                    Column(
                        modifier = Modifier
                            .height(80.dp)
                            .padding(10.dp), verticalArrangement = Arrangement.Center
                    ) {
                        Icon(

                            Icons.Rounded.ArrowForward, contentDescription = "test"
                        )
                    }
                }

            }
        }
    }
}