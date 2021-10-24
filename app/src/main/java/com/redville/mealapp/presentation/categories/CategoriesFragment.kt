package com.redville.mealapp.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.core.utils.LayoutType
import com.redville.mealapp.databinding.CategoriesFragmentBinding
import com.redville.mealapp.domain.model.Category
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CategoriesFragment : BaseFragment(R.layout.categories_fragment) {

    private lateinit var binding: CategoriesFragmentBinding
    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter() }
    private val categoriesViewModel by viewModels<CategoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CategoriesViewState.CategoriesReceived -> setUpAdapter(state.categories)
        }
    }

    private fun setUpAdapter(categories: List<Category>) {

        adapter.addData(categories)

        adapter.listener = {
            navController.navigate(CategoriesFragmentDirections.actionCategoriesFragmentToMealsFragment(it))
        }

        binding.rvCategories.apply {
            adapter = this@CategoriesFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = CategoriesFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        categoriesViewModel.doGetCategories("")

        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        LayoutType.GRID

    }

}