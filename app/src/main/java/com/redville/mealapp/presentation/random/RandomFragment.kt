package com.redville.mealapp.presentation.random

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.RandomFragmentBinding
import com.redville.mealapp.domain.model.Meal
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class RandomFragment : BaseFragment(R.layout.random_fragment) {

    private lateinit var binding: RandomFragmentBinding
    private val randomViewModel by viewModels<RandomViewModel>()
    private val adapter : MealAdapter by lazy { MealAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        randomViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is MealViewState.MealReceived -> setUpAdapter(state.meal)
        }
    }

    private fun setUpAdapter(meal: List<Meal>) {

        adapter.addData(meal)

        adapter.listener = {}

        binding.rvMeal.apply {
            adapter = this@RandomFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = RandomFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        randomViewModel.doGetRandomMeal("")

    }

}