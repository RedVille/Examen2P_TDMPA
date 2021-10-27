package com.redville.mealapp.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.extension.failure
import com.redville.mealapp.core.extension.observe
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.core.presentation.BaseViewState
import com.redville.mealapp.databinding.AccountFragmentBinding
import com.redville.mealapp.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class AccountFragment : BaseFragment(R.layout.account_fragment) {

    private lateinit var binding: AccountFragmentBinding
    private val accountViewModel by viewModels<AccountViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accountViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)

            getLocalUser()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is AccountViewState.LoggedUser -> {
                printUser()
            }
            is AccountViewState.UserNotFound -> {
                navController.navigate(AccountFragmentDirections.actionAccountFragmentToLogoutFragment())
            }
        }
    }

    override fun setBinding(view: View) {
        binding = AccountFragmentBinding.bind(view)

        binding.lifecycleOwner = this

    }

    fun printUser() {

    }

}