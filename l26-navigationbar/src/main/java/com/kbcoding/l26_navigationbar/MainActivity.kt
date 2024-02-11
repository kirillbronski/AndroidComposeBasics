package com.kbcoding.l26_navigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

enum class Tab(
    @StringRes val labelRes: Int,
    val icon: ImageVector,
) {
    Items(R.string.items, Icons.Default.List),
    Settings(R.string.settings, Icons.Default.Settings),
    Profile(R.string.profile, Icons.Default.AccountBox),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun AppScreen() {
    var currentTab by remember {
        mutableStateOf(Tab.Items)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopAppBar(
            title = { Text(text = stringResource(currentTab.labelRes)) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            )
        )

        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
        ) {
            TabScreen(tab = currentTab)
        }

        NavigationBar {
            Tab.entries.forEach { tab ->
                NavigationBarItem(
                    selected = tab == currentTab,
                    onClick = { currentTab = tab },
                    label = { Text(text = stringResource(tab.labelRes)) },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = stringResource(tab.labelRes),
                        )
                    },
                )
            }
        }

    }
}

@Composable
fun ItemsScreen() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Items Screen", fontSize = 28.sp)
    }
}

@Composable
fun SettingsScreen() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen", fontSize = 28.sp)
    }
}

@Composable
fun ProfileScreen() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Profile Screen", fontSize = 28.sp)
    }
}

@Composable
fun TabScreen(tab: Tab) {
    when (tab) {
        Tab.Items -> ItemsScreen()
        Tab.Settings -> SettingsScreen()
        Tab.Profile -> ProfileScreen()
    }
}