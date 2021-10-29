package com.redville.mealapp.core.plataform

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.redville.mealapp.domain.model.User
import com.squareup.moshi.Moshi
import javax.inject.Inject

class AuthManager @Inject constructor(private val context: Context) {

    private val PREFS_KEY = "USER_PREFS"
    private val USER_KEY = "USER"

    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
    private val moshi = Moshi.Builder().build()

    var user: User?
        set(value) = preferences.edit()
            .putString(USER_KEY, moshi.adapter(User::class.java).toJson(value)).apply()
        get() = preferences.getString(USER_KEY, null)?.let {
            return@let try {
                moshi.adapter(User::class.java).fromJson(it)
            } catch (e: Exception) {
                null
            }
        }
/*
    @SuppressLint("CommitPrefEdits")
    fun removePreferences() {
        preferences.edit().remove(PREFS_KEY)
    }*/
}