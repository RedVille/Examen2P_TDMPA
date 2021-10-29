package com.redville.mealapp.presentation.account.signup

import androidx.lifecycle.MutableLiveData
import com.redville.mealapp.core.presentation.BaseViewModel
import com.redville.mealapp.domain.model.User
import com.redville.mealapp.domain.usecase.GetUserByName
import com.redville.mealapp.domain.usecase.SaveUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getUserByName: GetUserByName,
    private val saveUser: SaveUser
) : BaseViewModel() {
    val name = MutableLiveData("")
    val pass = MutableLiveData("")
    val rePass = MutableLiveData("")

    //region actions

    fun doGetUserByName(string: String) {
        getUserByName(string) {
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

    //endregion

    //region validations

    fun validateInputs(): String {
        name
        pass
        rePass
        // empties
        if (name.value.toString().isBlank() || pass.value.toString().isBlank() ||
            rePass.value.toString().isBlank()) return "Don't leave empty fields!! D:"

        // password length
        if (pass.value.toString().length < 5) return "Ur password must have minimum 5 characters"

        // password
        if (pass.value.toString() != rePass.value.toString()) return "Ur password doesn't match ):"

        return ""
    }

    //endregion

}