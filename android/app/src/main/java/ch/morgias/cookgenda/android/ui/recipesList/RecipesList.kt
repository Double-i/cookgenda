package ch.morgias.cookgenda.android.ui.recipesList

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.morgias.cookgenda.android.models.Recipe
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ch.morgias.cookgenda.android.R


@Composable
fun RecipesList(list: List<Recipe>) {
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Yellow)
                        .defaultMinSize(minHeight = 80.dp)
                        .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(15))
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.recipe),
                        "Recipe image",
                        modifier = Modifier
                            .height(80.dp)
                            .border(0.dp, Color.Transparent, shape = RoundedCornerShape(50))
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("COUCOU")
                        Text("COUCOU2")
                    }
                    Column(modifier = Modifier.height(80.dp)) {
                        Text("COUCOU23")
                        Text("COUCOU23")
                        Text("COUCOU23")
                        Text("COUCOU23")
                        Text("COUCOU23")
                        Text("COUCOU23")
                        Icon(
                            Icons.Rounded.ArrowForward, contentDescription = "test"
                        )
                    }
                }

            }
        }
    }
}