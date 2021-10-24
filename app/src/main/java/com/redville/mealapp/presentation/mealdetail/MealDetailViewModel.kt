import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.domain.usecase.GetMeals
import com.redville.mealapp.domain.usecase.GetMealsByName
import com.redville.mealapp.domain.usecase.SaveMeals
import com.redville.mealapp.presentation.meals.MealsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMeals: GetMeals,
    private val getMealsByName: GetMealsByName,
    private val saveMeals: SaveMeals
) : BaseViewModel() {

    fun doGetMealByName(name: String) {
        getMealsByName(name) {
            it.fold(::handleFailure) {
                state.value = MealsViewState.MealsReceived(it.meals ?: listOf())

                saveMeals(it.meals ?: listOf())

                true
            }
        }
    }

}