package ch.morgias.cookgenda.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.morgias.cookgenda.android.ui.Course
import ch.morgias.cookgenda.android.ui.Planning
import ch.morgias.cookgenda.android.ui.recipesList.RecipesList

@Composable
fun GraphRouter(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.RecipesListScreen.route) {
        composable(route = Screen.CourseListScreen.route) {
            Course()
        }
        composable(route = Screen.PlanningListScreen.route) {
            Planning()
        }
        composable(route = Screen.RecipesListScreen.route) {
            RecipesList(navController)
        }
    }
}