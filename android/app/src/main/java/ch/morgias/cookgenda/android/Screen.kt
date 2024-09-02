package ch.morgias.cookgenda.android

sealed class Screen(val route: String) {
    object RecipesListResumesScreen : Screen(route = "recipesList")
    object RecipeDetailsScreen : Screen(route = "recipes/{recipeId}") {
        fun withRecipeId(recipeId: Int): String {
            return route.replace("{recipeId}", recipeId.toString())
        }
    }

    object PlanningListScreen : Screen(route = "planning")
    object ShoppingListResumesScreen : Screen(route = "shoppingList")
    object ShoppingListScreen : Screen(route = "shoppingList/{shoppingListId}") {
        fun withShoppingListId(shoppingListId: Long): String {
            return route.replace("{shoppingListId}", shoppingListId.toString())
        }
    }
}