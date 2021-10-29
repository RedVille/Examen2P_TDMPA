package com.redville.mealapp.data.dto

import com.redville.mealapp.domain.model.Like
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LikesResponse(val likes: List<Like> = listOf())