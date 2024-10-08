package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.models.PlanedRecipe
import ch.morgias.cookgenda.android.models.PlaningDay
import ch.morgias.cookgenda.android.models.RecipeDetails
import ch.morgias.cookgenda.android.models.dto.NewPlanedRecipeDto
import ch.morgias.cookgenda.android.network.PlaningApi
import ch.morgias.cookgenda.android.network.RecipeApi
import ch.morgias.cookgenda.android.network.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate


class RecipeDetailsViewModel : ViewModel() {
    private var _selectedRecipe: MutableLiveData<RecipeDetails?> = MutableLiveData()
    var selectedMonday = LocalDate.now().with(DayOfWeek.MONDAY)
    fun changeMonday(offsetFromToday: Long) {
        selectedMonday = selectedMonday.plusDays(offsetFromToday * 7)
    }


    val planningMode: LiveData<Boolean> = _selectedRecipe.switchMap { recipe ->
        MutableLiveData(recipe != null)
    }
    var selectedRecipe: LiveData<RecipeDetails?> = _selectedRecipe

    private val _recipeUiState: MutableStateFlow<RequestState> =
        MutableStateFlow(RequestState.Loading)
    val uiState: StateFlow<RequestState> = _recipeUiState.asStateFlow()


    private val _planningUiState: MutableStateFlow<RequestState> = MutableStateFlow(
        RequestState.Loading
    )
    val planningUiState: StateFlow<RequestState> = _planningUiState.asStateFlow()


    fun selectRecipe(recipe: RecipeDetails) {
        _selectedRecipe.value = recipe
    }

    fun getPlanedRecipeForSpecificWeek(from: LocalDate, to: LocalDate) {
        viewModelScope.launch {
            _planningUiState.value = try {
                RequestState.Success(PlaningApi.retrofitService.getPlaningForWeek(from, to))
            } catch (ex: Exception) {
                Log.e("planning", ex.message!!, ex.cause)
                RequestState.Error
            }
        }
    }

    fun getRecipeDetail(id: Int) {
        viewModelScope.launch {
            _recipeUiState.value = try {
                Log.i("RecipeDetailsViewModel - getRecipeDetail", "données recette ")
                RequestState.Success(RecipeApi.retrofitService.getRecipe(id))
            } catch (ex: Exception) {
                Log.e("t", ex.message!!, ex.cause)
                RequestState.Error
            }
        }
    }

    fun addToPlaning(recipeId: Int, date: LocalDate) {
        viewModelScope.launch {
            _planningUiState.value = try {
                val t2 = PlaningApi.retrofitService.planRecipe(
                    NewPlanedRecipeDto(
                        recipeId,
                        date.atStartOfDay()
                    )
                );
                val t = (_planningUiState.value as RequestState.Success<List<PlaningDay>>).result

                val newList = ArrayList<PlaningDay>()
                t.forEach {
                    val recipes = ArrayList<PlanedRecipe>()
                    recipes.addAll(it.recipes)
                    if (it.date == date.atStartOfDay()) {
                        recipes.add(PlanedRecipe(recipeId, t2.planedRecipeId, t2.name, t2.date))
                    }
                    newList.add(PlaningDay(it.date, recipes))
                }

                RequestState.Success(newList)

            } catch (ex: Exception) {
                Log.e("t", ex.message!!, ex.cause)
                RequestState.Error
            }
        }
    }

    fun deletePlanedRecipe(planedRecipeId: Int) {
        viewModelScope.launch {
            _planningUiState.value = try {
                PlaningApi.retrofitService.deletePlanedRecipe(planedRecipeId);
                val planingDaysList =
                    (_planningUiState.value as RequestState.Success<List<PlaningDay>>).result

                val newList = ArrayList<PlaningDay>()
                planingDaysList.forEach { planingDay ->
                    newList.add(
                        PlaningDay(
                            planingDay.date,
                            planingDay.recipes.filter { planedRecipe ->
                                planedRecipe.planedRecipeId != planedRecipeId
                            })
                    )

                }
                RequestState.Success(newList)
            } catch (ex: Exception) {
                Log.e("VM - deleting", ex.message!!, ex.cause)
                RequestState.Error
            }
        }
    }

    fun removeSelection() {
        _selectedRecipe.value = null
    }
}