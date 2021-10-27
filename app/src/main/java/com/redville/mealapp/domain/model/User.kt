package com.redville.mealapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val pfp: Int = 1
) {
}