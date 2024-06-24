package com.example.exchangesapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exchangesapp.data.ConvertedExchangesModel
import com.example.exchangesapp.data.ExchangesModel
import com.example.exchangesapp.utils.Constants
import com.example.exchangesapp.utils.TableCell
import com.example.exchangesapp.utils.listOfConvertedExchanges
import com.example.exchangesapp.utils.listOfNullConvertedExchanges


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCitiesMenu(options: List<String>, menuSelectedOption: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }

    Column (modifier = Modifier.padding(bottom = 50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Выберите город",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = menuSelectedOption.value,
                onValueChange = {
                },
                label = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = selectionOption,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        onClick = {
                            menuSelectedOption.value = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TableScreen(exchanges: State<List<ExchangesModel>?>) {
    var tableData = listOf<ConvertedExchangesModel>()
    if (exchanges.value != null) {
        exchanges.value!!.forEach { element ->
            tableData = listOfConvertedExchanges(element)
        }
    } else {
        tableData = listOfNullConvertedExchanges()
    }
    val column1Weight = .3f
    val column2Weight = .35f
    val column3Weight = .35f
    LazyColumn(
        Modifier
            .padding(16.dp)
    ) {
        item {
            Row(Modifier.background(Color.LightGray)) {
                TableCell(text = "", weight = column1Weight)
                TableCell(text = Constants.SELL, weight = column2Weight)
                TableCell(text = Constants.BUY, weight = column3Weight)
            }
        }
        items(tableData) { element ->
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = element.name, weight = column1Weight)
                TableCell(text = element.inValue, weight = column2Weight)
                TableCell(text = element.outValue, weight = column3Weight)
            }
        }
    }
}
