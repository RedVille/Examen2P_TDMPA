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
class Meal(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "idMeal") val idMeal: Int = 0,
    @Json(name = "strMeal") val name: String = "",
    @Json(name = "strCategory") val category: String = "",
    @Json(name = "strArea") val area: String = "",
    @Json(name = "strInstructions") val instructions: String? = "",
    @Json(name = "strMealThumb") val urlThumb: String = "",
    @Json(name = "strImageSource") val urlImage: String? = "",
    @Json(name = "strSource") val urlsource: String? = "",
    @Json(name = "strYoutube") val urlyoutube: String? = ""
) : Parcelable {

    val urlDetail: String
        get() = urlImage ?: urlThumb

}