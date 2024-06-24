package com.example.exchangesapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.exchangesapp.ui.viewModels.ExchangesViewModel
import com.example.exchangesapp.utils.Constants

@Composable
fun MainScreen(
    exchangesViewModel: ExchangesViewModel
) {
    with(exchangesViewModel) {
        val exchanges = exchanges.collectAsStateWithLifecycle()
        val loading = loading.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            val options = listOf(
                Constants.BREST,
                Constants.VITEBSK,
                Constants.GOMEL,
                Constants.GRODNO,
                Constants.MINSK,
                Constants.MOGILEV
            )
            val selectedOptionText = remember {
                mutableStateOf(options[0])
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                DropDownCitiesMenu(options, selectedOptionText)
                TableScreen(exchanges)
            }

            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(5.dp),
                    onClick = {
                        requestExchanges(selectedOptionText.value)
                    }
                ) {
                    Text(text = Constants.SHOW_EXCHANGES, fontSize = 16.sp)
                }
            }
        }

        if (loading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .pointerInput(Unit) {},
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}