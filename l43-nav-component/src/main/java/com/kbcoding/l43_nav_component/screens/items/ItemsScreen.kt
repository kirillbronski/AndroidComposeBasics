package com.kbcoding.l43_nav_component.screens.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbcoding.l43_nav_component.screens.LocalNavController
import com.kbcoding.l43_nav_component.screens.Route

@Composable
fun ItemsScreen() {

    val navController = LocalNavController.current

    ItemsContent(
        onLaunchAddItemScreen = {
            navController.navigate(Route.AddItem.path)
        }
    )
}

@Composable
fun ItemsContent(
    onLaunchAddItemScreen: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Items Screen",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
        FloatingActionButton(
            onClick = onLaunchAddItemScreen,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ItemsScreenPreview() {
    ItemsContent(onLaunchAddItemScreen = {})
}