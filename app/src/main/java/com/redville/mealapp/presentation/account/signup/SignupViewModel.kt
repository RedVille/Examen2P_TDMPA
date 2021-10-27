package com.redville.mealapp.presentation.account.signup

import androidx.lifecycle.ViewModel
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.domain.usecase.GetUsers
import com.redville.mealapp.domain.usecase.SaveUser
import com.redville.mealapp.presentation.account.AccountViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getUsers: GetUsers,
    private val saveUser: SaveUser
) : BaseViewModel() {

    fun doGetUsers(string: String) {
        getUsers(string) {
            it.fold(::handleFailure) {
                state.value = UsersViewState.UsersReceived(it.users?: listOf())

                true
            }
        }
    }

    fun saveUsers(user: List<User>) {
        saveUser(user) {
            it.fold(::handleFailure) {
                it
            }
        }
    }

}