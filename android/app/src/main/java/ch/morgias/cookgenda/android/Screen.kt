package ch.morgias.cookgenda.android

sealed class Screen(val route: String) {
    object RecipesListResumesScreen : Screen(route = "recipesListResumes")
    object RecipesListScreen : Screen(route = "recipesList/recipesListId")
    object RecipeDetailsScreen : Screen(route = "recipes/{recipeId}") {
        fun withRecipeId(recipeId: Int): String {
            return route.replace("{recipeId}", recipeId.toString())
        }
    }

    object PlanningListScreen : Screen(route = "planning")
    object CourseListScreen : Screen(route = "courses")
}