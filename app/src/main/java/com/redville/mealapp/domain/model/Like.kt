package com.redville.mealapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
class Like(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nameUser: String = "",
    val nameMeal: String = ""
)