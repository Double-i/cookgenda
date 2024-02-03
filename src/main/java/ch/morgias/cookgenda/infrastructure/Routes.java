package ch.morgias.cookgenda.infrastructure;

public class Routes {
    public static final String BASE_API = "/api/v1";
    public static final String FOOD_INDEX = BASE_API + "/foods";
    public static final String RECIPE_INDEX = BASE_API + "/recipes";
    public static final String PLANED_RECIPE_CREATE = BASE_API + "/recipes/{recipeId}/planed";
    public static final String PLANED_RECIPE_INDEX_PERIOD = BASE_API + "/planedRecipes";
    public static final String SHOPPING_LIST_GENERATE_FROM_TO = BASE_API + "/shoppingLists/generate";
}
