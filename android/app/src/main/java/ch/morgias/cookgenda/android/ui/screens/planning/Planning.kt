package ch.morgias.cookgenda.android.ui.screens.planning

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.models.Recipe

val list = listOf<Recipe>(
    Recipe(1, "test"),
    Recipe(1, "test"),

    Recipe(1, "test"),
    Recipe(1, "test"),

    Recipe(1, "test"),
    Recipe(1, "test"),

    Recipe(1, "test"),
    Recipe(1, "test"),

    Recipe(1, "test"),
    Recipe(1, "test"),

    Recipe(1, "test"),
    Recipe(1, "test"),

    Recipe(1, "test"),
    Recipe(1, "test"),
)

@Composable
fun Planning(navController: NavHostController) {
    Column {
        Row {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(list) { recipe ->
                    val shape = RoundedCornerShape(20)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(5.dp, shape)
                            .background(color = Color.Green)
                            .height(80.dp)
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
                            Text(recipe.name)
                            Text(color = Color.Gray, text = "COUCOU2")
                        }
                        Column(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(10.dp), verticalArrangement = Arrangement.Center
                        ) {
                            Icon(

                                Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "test"
                            )
                        }
                    }
                }
            }
        }
    }
}