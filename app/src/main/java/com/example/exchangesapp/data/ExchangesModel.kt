package com.example.exchangesapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangesModel(
    val EUR_in: String = "",
    val EUR_out: String = "",
    val RUB_in: String = "",
    val RUB_out: String = "",
    val USD_in: String = "",
    val USD_out: String = "",
    val name: String = ""
): Parcelable