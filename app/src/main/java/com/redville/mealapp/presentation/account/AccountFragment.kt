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

    private var pfps: List<Int> = listOf(R.drawable.ic_pfp1, R.drawable.ic_pfp2, R.drawable.ic_pfp3,
        R.drawable.ic_pfp4, R.drawable.ic_pfp5, R.drawable.ic_pfp6,
        R.drawable.ic_pfp7, R.drawable.ic_pfp8, R.drawable.ic_pfp9,
        R.drawable.ic_pfp10, R.drawable.ic_pfp11, R.drawable.ic_pfp12,
        R.drawable.ic_pfp13, R.drawable.ic_pfp14, R.drawable.ic_pfp15,
        R.drawable.ic_pfp16, R.drawable.ic_pfp17, R.drawable.ic_pfp18,
        R.drawable.ic_pfp19, R.drawable.ic_pfp20, R.drawable.ic_pfp21,
        R.drawable.ic_pfp22, R.drawable.ic_pfp23, R.drawable.ic_pfp24)

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
                accountViewModel.getLocalUser()
                printUser(state.user)
            }
            is AccountViewState.UserNotFound -> {
                navController.navigate(AccountFragmentDirections.actionAccountFragmentToLogoutFragment())
            }
        }
    }

    override fun setBinding(view: View) {
        binding = AccountFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        binding.btnLogout.setOnClickListener {
            accountViewModel.removeLocalUser()
            navController.navigate(AccountFragmentDirections.actionAccountFragmentToLogoutFragment())
        }
    }

    private fun printUser(activeUser: User) {
        binding.tvUsername.text = activeUser.name
        binding.imgPfp.setImageResource(pfps[activeUser.pfp])
    }

}