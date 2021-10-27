package com.redville.mealapp.data.dto

import com.redville.mealapp.domain.model.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponse(val users: List<User>? = listOf())