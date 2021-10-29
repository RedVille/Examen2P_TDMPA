package com.redville.mealapp.presentation.meals

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.MealsFragmentBinding
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.presentation.account.AccountViewState
import com.redville.mealapp.presentation.likes.LikesViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class MealsFragment : BaseFragment(R.layout.meals_fragment) {

    private lateinit var binding: MealsFragmentBinding
    private val adapter : MealsAdapter by lazy { MealsAdapter() }
    private val mealsViewModel by viewModels<MealsViewModel>()
    private val args: MealsFragmentArgs by navArgs()

    //likes vars
    private var actualLikedMeal: String = ""
    private var actualUser: String = ""
    private var actualLike: Like = Like()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealsViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is MealsViewState.MealsReceived -> setUpAdapter(state.meals)
            is AccountViewState.LoggedUser -> setUser(state.user.name)
            is AccountViewState.UserNotFound -> showToast("U need to have an account to like meals")
            is LikesViewState.LikesReceived -> checkLike(likeState(state.likes))//changeLike(likeState(state.likes))
        }
    }

    private fun setUpAdapter(meals: List<Meal>) {

        adapter.addData(meals)

        adapter.listener = {
            mealsViewModel.doGetMealsById(it.idMeal)

            // show likes
            setMeal(it.name)

            navController.navigate(MealsFragmentDirections.actionMealsFragmentToMealdetailFragment(it))

        }

        binding.rvMeals.apply {
            adapter = this@MealsFragment.adapter
        }
    }

    private fun setMeal(mealName: String) {
        mealsViewModel.getLocalUser()
        actualLikedMeal = mealName
    }

    private fun setUser(user: String) {
        mealsViewModel.doGetLikeByUser(user)
        actualUser = user
    }

    private fun likeState(likes: List<Like>): Boolean {
        likes.forEach{
            if (it.nameMeal == actualLikedMeal) {
                actualLike = it
                return false
            }
        }
        return true
    }

    private fun checkLike(like: Boolean) {
        // no se agregado por lo tanto es un like vacío
        if (like) adapter.addLike(false)
        else adapter.addLike(true)
    }

    private fun changeLike(like: Boolean) {
        if (like) {
            // like
            mealsViewModel.saveLike(listOf(Like(0, actualUser, actualLikedMeal)))
        } else {
            // dislike
            mealsViewModel.deleteLike(listOf(actualLike))
        }
    }

    override fun setBinding(view: View) {
        binding = MealsFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        mealsViewModel.doGetMeals(args.category.name)

        binding.svMeals.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showMeals(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                showMeals(newText ?: "")
                return true
            }
        })

    }

    private fun showMeals(name: String) {
        when (name) {
            "" -> {
                mealsViewModel.doGetMeals(args.category.name)
            }
            else -> {
                mealsViewModel.doGetMealsByName(name)
            }
        }
    }

}