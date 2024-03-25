package ch.morgias.cookgenda.android

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ch.morgias.cookgenda.android.ui.screens.courses.Course
import ch.morgias.cookgenda.android.ui.screens.planning.Planning
import ch.morgias.cookgenda.android.ui.screens.recipesDetails.RecipeDetails
import ch.morgias.cookgenda.android.ui.screens.recipesDetails.RecipeDetailsViewModel
import ch.morgias.cookgenda.android.ui.screens.recipesExplorer.RecipeViewModel
import ch.morgias.cookgenda.android.ui.screens.recipesExplorer.RecipesList

@Composable
fun GraphRouter(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.RecipesListScreen.route) {
        composable(route = Screen.CourseListScreen.route) {
            Course()
        }
        composable(route = Screen.PlanningListScreen.route) {
            Planning()
        }
        composable(
            route = Screen.RecipesListScreen.route,

            ) {
            val vm = viewModel<RecipeViewModel>()
            RecipesList(navController, vm)
        }
        composable(
            route = Screen.RecipeDetailsScreen.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) {
            val vm = viewModel<RecipeDetailsViewModel>()
            RecipeDetails(it.arguments!!.getInt("recipeId"), vm)
        }
    }
}