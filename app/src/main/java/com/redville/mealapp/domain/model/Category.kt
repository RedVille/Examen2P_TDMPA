package com.redville.mealapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
class Category (
@PrimaryKey(autoGenerate = false)
@Json(name = "idCategory") val idCategory: Int = 0,
@Json(name = "strCategory") val name: String = "",
@Json(name = "strCategoryThumb") val urlThumb: String = "",
@Json(name = "strCategoryDescription") val description: String? = ""
) : Parcelable {
}