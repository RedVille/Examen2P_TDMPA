package com.redville.mealapp.presentation.account.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.SignupFragmentBinding
import com.redville.mealapp.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class SignupFragment : BaseFragment(R.layout.signup_fragment) {

    private lateinit var binding: SignupFragmentBinding
    private val signupViewModel by viewModels<SignupViewModel>()
    //private var imgPfp: Pfp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun setBinding(view: View) {
        binding = SignupFragmentBinding.bind(view)

        binding.apply {
            vmSignup = signupViewModel

            lifecycleOwner = this@SignupFragment

            setListener()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is UsersViewState.UsersReceived -> validateExistingUser(state.users)
        }
    }

    private fun setListener() {
        binding.btnSignUp.setOnClickListener {
            if (validateInputs()) signupViewModel.doGetUserByName(binding.etUsername.text.toString())
        }
        binding.imgNext.setOnClickListener {
            getNextPfp()
        }
        binding.imgPrevious.setOnClickListener {
            getPreviousPfp()
        }
    }

    //region DoSignUp

    private fun doSignup() {
        val user = User(0,binding.etUsername.text.toString(), binding.etPassword.text.toString(), 1)
        val newUser = listOf(user)

        signupViewModel.saveUsers(newUser)
        showToast("SignedUp successfully ^^")
        navController.navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
    }

    private fun validateInputs(): Boolean {
        if (signupViewModel.validateInputs().isBlank()) return true
        else showToast(signupViewModel.validateInputs())
        return false
    }

    private fun validateExistingUser(existingUser: List<User>) {
        if (existingUser.isEmpty()) doSignup()
        else showToast("User already existing :( \nChoose another one")
    }

    //endregion

    //region Pfp

    private fun setPfp() {

    }

    private fun getPreviousPfp() {

    }

    private fun getNextPfp() {

    }

    //endregion

}