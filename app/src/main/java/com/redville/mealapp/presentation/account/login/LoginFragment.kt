package com.redville.mealapp.presentation.account.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.LoginFragmentBinding
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.presentation.account.signup.UsersViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    //region overrides

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is UsersViewState.UsersReceived -> doLogin(state.users[0])
        }
    }

    override fun setBinding(view: View) {
        binding = LoginFragmentBinding.bind(view)

        binding.apply {
            vmLogin = loginViewModel

            lifecycleOwner = this@LoginFragment

            setListener()
        }
    }

    //endregion

    //region private functions
    private fun setListener() {
        binding.btnLogin.setOnClickListener {
            if (loginViewModel.validateInputs().isBlank()) searchUser()
            else showToast(loginViewModel.validateInputs())
        }
    }

    private fun searchUser() = loginViewModel.doGetUserByName(binding.etUser.text.toString())

    private fun doLogin(userLogging: User) {

        if (loginViewModel.validatePassword(userLogging.password).isBlank()) {
            // equal passwords
            loginViewModel.saveLogin(userLogging)
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToAccountFragment())

        } else showToast(loginViewModel.validatePassword(userLogging.password))

    }

    //endregion

}