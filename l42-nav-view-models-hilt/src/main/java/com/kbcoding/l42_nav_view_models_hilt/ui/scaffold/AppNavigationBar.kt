package com.kbcoding.l42_nav_view_models_hilt.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l42_nav_view_models_hilt.ui.RootTabs

/**
 * In-app bottom navigation bar,
 */
@Composable
fun AppNavigationBar(
    currentIndex: Int,
    onIndexSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        RootTabs.forEachIndexed { index, tab ->
            val environment = remember(tab) {
                tab.screenProducer().environment
            }
            val icon = environment.icon
            if (icon != null) {
                NavigationBarItem(
                    selected = currentIndex == index,
                    label = { Text(stringResource(environment.titleRes)) },
                    onClick = { onIndexSelected(index) },
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppNavigationBarPreview() {
    AppNavigationBar(
        currentIndex = 0,
        onIndexSelected = {},
    )
}