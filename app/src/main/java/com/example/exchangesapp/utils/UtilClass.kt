package com.example.exchangesapp.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.exchangesapp.data.ConvertedExchangesModel
import com.example.exchangesapp.data.ExchangesModel

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

fun listOfConvertedExchanges(element: ExchangesModel): List<ConvertedExchangesModel> =
    listOf(
        ConvertedExchangesModel(
            name = Constants.USD,
            inValue = element.USD_in,
            outValue = element.USD_out
        ),
        ConvertedExchangesModel(
            name = Constants.EUR,
            inValue = element.EUR_in,
            outValue = element.EUR_out
        ),
        ConvertedExchangesModel(
            name = Constants.RUB,
            inValue = element.RUB_in,
            outValue = element.RUB_out
        )
    )

fun listOfNullConvertedExchanges(): List<ConvertedExchangesModel> =
    listOf(
        ConvertedExchangesModel(
            name = Constants.USD,
            inValue = "",
            outValue = ""
        ),
        ConvertedExchangesModel(
            name = Constants.EUR,
            inValue = "",
            outValue = ""
        ),
        ConvertedExchangesModel(
            name = Constants.RUB,
            inValue = "",
            outValue = ""
        )
    )