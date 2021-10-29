package com.redville.mealapp.presentation.account.login

import androidx.lifecycle.MutableLiveData
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.domain.usecase.GetUserByName
import com.redville.mealapp.domain.usecase.SaveLocalUser
import com.redville.mealapp.presentation.account.signup.UsersViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserByName: GetUserByName,
    private val saveLocalUser: SaveLocalUser
) : BaseViewModel() {

    val username = MutableLiveData("")
    val password = MutableLiveData("")

    //region actions

    fun doGetUserByName(string: String) {
        getUserByName(string) {
            it.fold(::handleFailure) {
                state.value = UsersViewState.UsersReceived(it.users?: listOf())

                true
            }
        }
    }

    fun saveLogin(newLoggedUser: User) {
        // saves new state
        saveLocalUser(newLoggedUser) {
            it.fold(::handleFailure) {
                it
            }
        }
    }

    //endregion

    //region validations

    fun validateInputs(): String {
        username
        password

        if (username.value.toString().isBlank() || password.value.toString().isBlank()) return "Don't leave empty fields!! D:"

        return ""
    }

    fun validatePassword(correctPassword: String): String {
        password

        if (password.value.equals(correctPassword)) return ""

        return "Incorrect Password è_é"
    }

    //endregion

}