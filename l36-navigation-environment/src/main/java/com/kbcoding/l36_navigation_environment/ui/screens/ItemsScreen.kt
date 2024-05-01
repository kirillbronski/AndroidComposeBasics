package com.kbcoding.l36_navigation_environment.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l36_navigation_environment.ItemsRepository
import com.kbcoding.l36_navigation_environment.R
import com.kbcoding.l36_navigation_environment.ui.AppRoute
import com.kbcoding.l36_navigation_environment.ui.AppScreen
import com.kbcoding.l36_navigation_environment.ui.AppScreenEnvironment
import com.kbcoding.l36_navigation_environment.ui.FloatingAction
import com.kbcoding.navigationstack.navigation.LocalRouter
import com.kbcoding.navigationstack.navigation.Router

val ItemsScreenProducer = { ItemsScreen() }

class ItemsScreen : AppScreen {
    private var router: Router? = null

    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.items
        icon = Icons.Default.List
        floatingAction = FloatingAction(
            icon = Icons.Default.Add,
            onClick = {
                router?.launch(AppRoute.AddItem)
            },
        )
    }

    @Composable
    override fun Content() {
        router = LocalRouter.current
        val itemsRepository = ItemsRepository.get()
        val items by itemsRepository.getItems().collectAsStateWithLifecycle()
        val isEmpty by remember {
            derivedStateOf { items.isEmpty() }
        }
        ItemsContent(
            isEmpty = isEmpty,
            items = { items },
        )
    }

}

@Composable
fun ItemsContent(
    isEmpty: Boolean,
    items: () -> List<String>,
) {
    if (isEmpty) {
        Text(
            text = stringResource(R.string.no_items),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
        )
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items()) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(all = 8.dp),
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ItemsPreview() {
    ItemsContent(
        items = { listOf("111", "222", "333") },
        isEmpty = false,
    )
}