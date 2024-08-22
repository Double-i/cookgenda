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
import androidx.compose.material3.MaterialTheme
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
import ch.morgias.cookgenda.android.models.PlaningDay
import ch.morgias.cookgenda.android.models.Recipe
import ch.morgias.cookgenda.android.models.createWeekPlaning
import java.time.format.DateTimeFormatter

val list = createWeekPlaning()

@Composable
fun DayPlaning(day: PlaningDay) {
    Column(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = day.date.format(DateTimeFormatter.ofPattern("EEEE (dd.MM)")),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
        )
        day.recipe.forEach { r -> DayMealPlaning(recipe = r) }
    }
}

@Composable
fun DayMealPlaning(recipe: Recipe) {
    val shape = RoundedCornerShape(20)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .background(color = Color.Green)
            .height(79.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.recipe),
            "Recipe image",
            modifier = Modifier
                .height(79.dp)
                .width(79.dp)
                .clip(RoundedCornerShape(14))
        )
        Column(
            modifier = Modifier
                .weight(10F)
                .padding(9.dp)
        ) {
            Text(recipe.name)
            Text(color = Color.Gray, text = "COUCOU1")
        }
        Column(
            modifier = Modifier
                .height(79.dp)
                .padding(9.dp), verticalArrangement = Arrangement.Center
        ) {
            Icon(

                Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = "test"
            )
        }
    }
}

@Composable
fun Planning(navController: NavHostController) {
    Column {
        Row {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(list) { day ->
                    DayPlaning(day = day)
                }
            }
        }
    }
}