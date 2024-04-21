package com.kbcoding.l34_navigationscreens.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcoding.l34_navigationscreens.ItemsRepository
import com.kbcoding.l34_navigationscreens.R
import com.kbcoding.navigationstack.navigation.LocalRouter


@Composable
fun AddItemScreen() {

    val itemsRepository = ItemsRepository.get()
    val router = LocalRouter.current

    AddItemContent(
        onSubmitNewItem = { newItemValue ->
            itemsRepository.addItem(newItemValue)
            router.pop()
        }
    )
}

@Composable
fun AddItemContent(onSubmitNewItem: (String) -> Unit) {
    var newItemValue by remember { mutableStateOf("") }
    val isAddEnabled by remember {
        derivedStateOf { newItemValue.isNotEmpty() }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        OutlinedTextField(
            value = newItemValue,
            label = { Text(stringResource(R.string.enter_new_value)) },
            singleLine = true,
            onValueChange = { newValue ->
                newItemValue = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = isAddEnabled,
            onClick = { onSubmitNewItem(newItemValue) }
        ) {
            Text(text = stringResource(R.string.add_new_item))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddItemPreview() {
    AddItemContent(onSubmitNewItem = {})
}