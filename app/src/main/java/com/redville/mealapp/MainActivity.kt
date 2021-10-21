package com.redville.mealapp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.redville.mealapp.core.presentation.BaseActivity
import com.redville.mealapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun layoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
    }

    override val fragmentContainer: FragmentContainerView
        get() = binding.fcv

    override fun setUpNavigation(navController: NavController) =
        NavigationUI.setupWithNavController(binding.bnvMenu, navController)

    override fun showProgress(show: Boolean) {
        //binding.progressView.isVisible = show
    }

    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId())
    }


}