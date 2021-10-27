package com.redville.mealapp.presentation.account.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.databinding.SignupFragmentBinding
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.presentation.account.AccountFragmentDirections
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

        binding.lifecycleOwner = this

        setListener()
    }

    private fun setListener() {
        binding.btnSignUp.setOnClickListener {
            if (validateInputs()) doSignup()
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
        val user: User = User(0, binding.etUsername.text.toString(), binding.etEmail.text.toString(),
            binding.etPassword.text.toString(), 1)
        val newUser = listOf<User>(user)

        signupViewModel.saveUsers(newUser)
        showToast("SignedUp successfully ^^")
        navController.navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
    }

    private fun validateInputs(): Boolean {
        // validate empties
        if (binding.etUsername.text.isNullOrBlank() || binding.etEmail.text.isNullOrBlank() ||
            binding.etPassword.text.isNullOrBlank() || binding.etRepeatPassword.text.isNullOrBlank()){
            showToast("Don't leave empty fields!! D:")
            return false
        }

        // validate existing user


        // validate password
        if (binding.etPassword.text.toString() != binding.etRepeatPassword.text.toString()){
            showToast("Ur password doesn't match ):")
            return false
        }

        setPfp()

        return true
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