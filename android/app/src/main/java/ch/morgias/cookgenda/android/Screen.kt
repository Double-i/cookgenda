package ch.morgias.cookgenda.android

sealed class Screen(val route: String) {
    object RecipesListScreen : Screen(route = "recipesList")
    object PlanningListScreen : Screen(route = "planning")
    object CourseListScreen : Screen(route = "courses")
}