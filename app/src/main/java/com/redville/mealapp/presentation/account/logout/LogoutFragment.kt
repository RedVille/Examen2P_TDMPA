package com.redville.mealapp.presentation.account.logout

import android.os.Bundle
import android.view.View
import com.redville.mealapp.R
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.databinding.LogoutFragmentBinding

class LogoutFragment : BaseFragment(R.layout.logout_fragment) {

    private lateinit var binding: LogoutFragmentBinding

    override fun setBinding(view: View) {
        binding = LogoutFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        setListener()
    }

    private fun setListener() {
        binding.btnGoToLogin.setOnClickListener {
            navController.navigate(LogoutFragmentDirections.actionLogoutFragmentToLoginFragment())
        }
        binding.btnGoToSignUp.setOnClickListener {
            navController.navigate(LogoutFragmentDirections.actionLogoutFragmentToSignupFragment())
        }
    }

}