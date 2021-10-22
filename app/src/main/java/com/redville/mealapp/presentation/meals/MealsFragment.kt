package com.redville.mealapp.presentation.meals

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.MealsFragmentBinding
import com.redville.mealapp.domain.model.Meal
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class MealsFragment : BaseFragment(R.layout.meals_fragment) {

    private lateinit var binding: MealsFragmentBinding
    private val adapter : MealsAdapter by lazy { MealsAdapter() }
    //private val mealsViewModel by viewModels<MealsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*mealsViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }*/
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is MealsViewState.MealsReceived -> setUpAdapter(state.meals)
        }
    }

    private fun setUpAdapter(meals: List<Meal>) {

        adapter.addData(meals)

        adapter.listener = {
            navController.navigate(MealsFragmentDirections.actionMealsFragmentToMealDetailFragment())
        }

        binding.rvMeals.apply {
            adapter = this@MealsFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = MealsFragmentBinding.bind(view)

        binding.lifecycleOwner = this

    }

}