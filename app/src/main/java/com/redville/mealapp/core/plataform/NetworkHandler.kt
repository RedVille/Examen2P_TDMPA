package com.redville.mealapp.core.plataform

import android.content.Context
import com.redville.mealapp.core.extension.networkInfo
import javax.inject.Inject

class NetworkHandler @Inject constructor(private val context: Context) {

    val isConnected get() = context.networkInfo?.isConnectedOrConnecting == true

}