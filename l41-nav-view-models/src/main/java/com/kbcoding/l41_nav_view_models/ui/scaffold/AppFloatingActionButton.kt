package com.kbcoding.l41_nav_view_models.ui.scaffold

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l41_nav_view_models.R
import com.kbcoding.l41_nav_view_models.ui.FloatingAction

/**
 * Floating Action Button from the right-bottom corner.
 * Now it is displayed only for ItemsScreen.
 */
@Composable
fun AppFloatingActionButton(
    floatingAction: FloatingAction?,
    modifier: Modifier = Modifier,
) {
    if (floatingAction != null) {
        FloatingActionButton(
            modifier = modifier,
            onClick = floatingAction.onClick
        ) {
            Icon(
                imageVector = floatingAction.icon,
                contentDescription = stringResource(R.string.add_new_item)
            )
        }
    }
}

@Preview
@Composable
private fun AppFloatingActionButtonPreview() {
    AppFloatingActionButton(
        floatingAction = null,
    )
}
