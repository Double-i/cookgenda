package ch.morgias.cookgenda.android

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ch.morgias.cookgenda.android.ui.screens.planning.Planning
import ch.morgias.cookgenda.android.ui.screens.recipesDetails.RecipeDetails
import ch.morgias.cookgenda.android.ui.screens.recipesDetails.RecipeDetailsViewModel
import ch.morgias.cookgenda.android.ui.screens.recipesExplorer.RecipeViewModel
import ch.morgias.cookgenda.android.ui.screens.recipesExplorer.RecipesList
import ch.morgias.cookgenda.android.ui.screens.shoppingList.ShoppingList
import ch.morgias.cookgenda.android.ui.screens.shoppingList.ShoppingListViewModel
import ch.morgias.cookgenda.android.ui.screens.shoppingList.ShoppingLists
import ch.morgias.cookgenda.android.ui.screens.shoppingList.ShoppingListsViewModel

@Composable
fun GraphRouter(navController: NavHostController) {
    val recipeDetailsVm = viewModel<RecipeDetailsViewModel>()
    NavHost(navController = navController, startDestination = Screen.RecipesListResumesScreen.route) {
        composable(route = Screen.ShoppingListResumesScreen.route) {
            val vm = viewModel<ShoppingListsViewModel>()
            ShoppingLists(navController, vm)
        }
        composable(route = Screen.ShoppingListScreen.route) {
            val vm = viewModel<ShoppingListViewModel>()
            ShoppingList(vm)
        }
        composable(route = Screen.PlanningListScreen.route) {
            Planning(navController, recipeDetailsVm)
        }
        composable(
            route = Screen.RecipesListResumesScreen.route,

            ) {
            val vm = viewModel<RecipeViewModel>()
            RecipesList(navController, vm)
        }
        composable(
            route = Screen.RecipeDetailsScreen.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) {
            RecipeDetails(navController, it.arguments!!.getInt("recipeId"), recipeDetailsVm)
        }
    }
}