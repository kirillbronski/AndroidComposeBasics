package com.kbcoding.l36_navigation_environment.ui.screens

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kbcoding.l36_navigation_environment.R
import com.kbcoding.l36_navigation_environment.ItemsRepository
import com.kbcoding.l36_navigation_environment.ui.AppRoute
import com.kbcoding.l36_navigation_environment.ui.AppScreen
import com.kbcoding.l36_navigation_environment.ui.AppScreenEnvironment
import com.kbcoding.navigationstack.navigation.LocalRouter


val AddItemScreenProducer = { AddItemScreen() }

class AddItemScreen : AppScreen {

    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.add_item
    }

    @Composable
    override fun Content() {
        val itemsRepository = ItemsRepository.get()
        val router = LocalRouter.current
        AddItemContent(
            onSubmitNewItem = { newValue ->
                itemsRepository.addItem(newValue)
                router.pop()
            },
            onLaunchSettingsScreen = {
                router.launch(AppRoute.Tab.Settings)
            }
        )
    }

}

@Composable
fun AddItemContent(
    onSubmitNewItem: (String) -> Unit,
    onLaunchSettingsScreen: () -> Unit,
) {
    var newItemValue by rememberSaveable { mutableStateOf("") }
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
        Button(
            onClick = onLaunchSettingsScreen,
        ) {
            Text(text = stringResource(R.string.settings))
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun AddItemPreview() {
    AddItemContent(onSubmitNewItem = {}, onLaunchSettingsScreen = {})
}