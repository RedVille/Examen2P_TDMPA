package com.redville.mealapp.presentation.mealdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.MealdetailFragmentBinding
import com.redville.mealapp.domain.model.Meal
import com.redville.mealapp.presentation.random.MealAdapter
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
    private val adapter : MealAdapter by lazy { MealAdapter() }
    private val args: MealdetailFragmentArgs by navArgs()

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
            is MealViewState.MealReceived -> setUpAdapter(state.meal)
        }
    }

    private fun setUpAdapter(meal: List<Meal>) {

        adapter.addData(meal)

        adapter.listener = {}

        binding.rvMeal.apply {
            adapter = this@MealdetailFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = MealdetailFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        mealdetailViewModel.doGetMealById(args.meal.idMeal)

    }

}