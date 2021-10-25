package com.redville.mealapp.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.redville.mealapp.R
import com.redville.mealapp.core.presentation.BaseFragment
import com.redville.mealapp.databinding.AccountFragmentBinding
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
        }
    }

    override fun setBinding(view: View) {
        binding = AccountFragmentBinding.bind(view)

        binding.lifecycleOwner = this

    }

}