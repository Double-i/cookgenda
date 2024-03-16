package ch.morgias.cookgenda.android.ui.recipesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.models.Recipe


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
                        .clip(RoundedCornerShape(20))
                        .defaultMinSize(minHeight = 80.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.recipe),
                        "Recipe image",
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(20))
                    )
                    Column(modifier = Modifier.weight(1F).padding(10.dp)) {
                        Text("COUCOU")
                        Text(color = Color.DarkGray, text = "COUCOU2")
                    }
                    Column(modifier = Modifier.height(80.dp)
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