package com.redville.mealapp.presentation.meals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.RowmealFragmentBinding
import com.redville.mealapp.domain.model.Like
import com.redville.mealapp.presentation.account.AccountViewState
import com.redville.mealapp.presentation.likes.LikesViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class RowmealFragment : BaseFragment(R.layout.rowmeal_fragment) {

    private lateinit var binding: RowmealFragmentBinding
    private val rowmealViewModel by viewModels<RowmealViewModel>()
    private var actualUser: String = ""
    private var actualLike: Like = Like()
    private var actualMeal: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rowmealViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is AccountViewState.LoggedUser -> setUser(state.user.name)
            is AccountViewState.UserNotFound -> showToast("U need to Login to like meals")
            is LikesViewState.LikesReceived -> changeLike(likeState(state.likes))
        }
    }

    override fun setBinding(view: View) {
        binding = RowmealFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        actualMeal = binding.txvName.text.toString()

        binding.imgLike.setOnClickListener {
            rowmealViewModel.getLocalUser()
        }
    }

    private fun setUser(user: String) {
        rowmealViewModel.doGetLikeByUser(user)
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
        if (like) {
            // like
            rowmealViewModel.saveLike(listOf(Like(0, actualUser, actualMeal)))
            binding.imgLike.setImageResource(R.drawable.ic_likeon)
        } else {
            // dislike
            rowmealViewModel.deleteLike(listOf(actualLike))
            binding.imgLike.setImageResource(R.drawable.ic_likeoff)
        }
    }

}