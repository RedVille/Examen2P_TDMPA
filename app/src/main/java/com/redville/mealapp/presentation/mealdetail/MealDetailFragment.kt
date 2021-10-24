package com.redville.mealapp.presentation.mealdetail

import android.view.View
import androidx.navigation.fragment.navArgs
import com.redville.mealapp.R
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.databinding.MealDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class MealDetailFragment : BaseFragment(R.layout.meal_detail_fragment) {

    private lateinit var binding: MealDetailFragmentBinding
    private val args: MealDetailFragmentArgs by navArgs()

    override fun setBinding(view: View) {

        binding = MealDetailFragmentBinding.bind(view)

        binding.apply {
            lifecycleOwner = this@MealDetailFragment
            meal = args.meal
        }

    }

}