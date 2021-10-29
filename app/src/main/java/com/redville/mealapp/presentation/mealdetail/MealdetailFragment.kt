package com.redville.mealapp.presentation.mealdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.loadFromURLCircular
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.MealdetailFragmentBinding
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.presentation.account.AccountViewState
import com.redville.mealapp.presentation.likes.LikesViewState
import com.redville.mealapp.presentation.random.MealViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class MealdetailFragment : BaseFragment(R.layout.mealdetail_fragment) {

    private lateinit var binding: MealdetailFragmentBinding
    private val mealdetailViewModel by viewModels<MealdetailViewModel>()
    private val args: MealdetailFragmentArgs by navArgs()
    private var actualUser: String = ""
    private var actualLike: Like = Like()
    private var actualMeal: String = ""
    private var changingLike: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealdetailViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is MealViewState.MealReceived -> printMeal(state.meal[0])
            is AccountViewState.LoggedUser -> setUser(state.user.name)
            is AccountViewState.UserNotFound -> showToast("U need to Login to like meals")
            is LikesViewState.LikesReceived -> changeLike(likeState(state.likes))
        }
    }

    private fun printMeal(meal: Meal) {
        binding.imgMeal.loadFromURLCircular(meal.urlDetail)
        binding.tvName.text = meal.name
        binding.tvArea.text = meal.area
        binding.tvCategory.text = meal.category
        binding.tvInstructions.text = meal.instructions
        actualMeal = meal.name
    }

    override fun setBinding(view: View) {
        binding = MealdetailFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        mealdetailViewModel.doGetMealById(args.meal.idMeal)

        mealdetailViewModel.getLocalUser()

        binding.imgLike.setOnClickListener {
            changingLike = true
            mealdetailViewModel.getLocalUser()
        }
    }

    private fun setUser(user: String) {
        mealdetailViewModel.doGetLikeByUser(user)
        actualUser = user
    }

    private fun likeState(likes: List<Like>): Boolean {
        likes.forEach{
            if (it.nameMeal == actualMeal) {
                actualLike = it
                return false
            }
        }
        return true
    }

    private fun changeLike(like: Boolean) {
        if (changingLike) {
            if (like) {
                // like
                mealdetailViewModel.saveLike(listOf(Like(0, actualUser, actualMeal)))
                binding.imgLike.setImageResource(R.drawable.ic_likeon)
            } else {
                // dislike
                mealdetailViewModel.deleteLike(listOf(actualLike))
                binding.imgLike.setImageResource(R.drawable.ic_likeoff)
            }
            changingLike = false
        }
        else {
            if (!like) binding.imgLike.setImageResource(R.drawable.ic_likeon)

        }

    }

}