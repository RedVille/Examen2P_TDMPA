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

    private var pfps: List<Int> = listOf(R.drawable.ic_pfp1, R.drawable.ic_pfp2, R.drawable.ic_pfp3,
                                        R.drawable.ic_pfp4, R.drawable.ic_pfp5, R.drawable.ic_pfp6,
                                        R.drawable.ic_pfp7, R.drawable.ic_pfp8, R.drawable.ic_pfp9,
                                        R.drawable.ic_pfp10, R.drawable.ic_pfp11, R.drawable.ic_pfp12,
                                        R.drawable.ic_pfp13, R.drawable.ic_pfp14, R.drawable.ic_pfp15,
                                        R.drawable.ic_pfp16, R.drawable.ic_pfp17, R.drawable.ic_pfp18,
                                        R.drawable.ic_pfp19, R.drawable.ic_pfp20, R.drawable.ic_pfp21,
                                        R.drawable.ic_pfp22, R.drawable.ic_pfp23, R.drawable.ic_pfp24)

    private lateinit var binding: SignupFragmentBinding
    private val signupViewModel by viewModels<SignupViewModel>()
    private var imgPfp: Int = 0

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
        val newUser = listOf(User(0,binding.etUsername.text.toString(), binding.etPassword.text.toString(), imgPfp))

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

    private fun getPreviousPfp() {

        if (imgPfp > 0) {
            imgPfp--
            binding.imgPfpSignUp.setImageResource(pfps[imgPfp])
        } else {
            imgPfp = pfps.size-1
            binding.imgPfpSignUp.setImageResource(pfps[imgPfp])
        }

    }

    private fun getNextPfp() {

        if (imgPfp < pfps.size-1) {
            imgPfp++
            binding.imgPfpSignUp.setImageResource(pfps[imgPfp])
        } else {
            imgPfp = 0
            binding.imgPfpSignUp.setImageResource(pfps[imgPfp])
        }

    }

    //endregion

}