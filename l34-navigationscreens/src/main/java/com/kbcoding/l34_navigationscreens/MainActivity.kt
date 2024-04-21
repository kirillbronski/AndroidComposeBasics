package com.kbcoding.l34_navigationscreens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.l34_navigationscreens.screens.AddItemScreen
import com.kbcoding.l34_navigationscreens.screens.ItemsScreen
import com.kbcoding.l34_navigationscreens.screens.ProfileScreen
import com.kbcoding.l34_navigationscreens.screens.SettingsScreen
import com.kbcoding.l34_navigationscreens.ui.theme.AppTheme
import com.kbcoding.navigationstack.navigation.NavigationHost
import com.kbcoding.navigationstack.navigation.rememberNavigation


/**
 * The list of all root tabs.
 */
val RootTabs =
    listOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(itemsRepository: ItemsRepository = ItemsRepository.get()) {

    val navigation = rememberNavigation(initialRoute = AppRoute.Tab.Items)
    val (router, navigationState) = navigation

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            (navigationState.currentRoute as? AppRoute)?.titleRes
                                ?: R.string.app_name
                        ),
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // Action on "Back" button in the toolbar
                            if (!navigationState.isRoot) router.pop()
                        }
                    ) {
                        Icon(
                            imageVector = if (navigationState.isRoot)
                                Icons.Default.Menu
                            else
                                Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.main_menu),
                        )
                    }
                },
                actions = {
                    var showPopupMenu by remember { mutableStateOf(false) }
                    val context = LocalContext.current
                    // "More" action button in the toolbar.
                    IconButton(
                        // just show popup menu:
                        onClick = { showPopupMenu = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.more_actions),
                        )
                    }

                    // popup menu itself with 2 items
                    DropdownMenu(
                        expanded = showPopupMenu,
                        onDismissRequest = { showPopupMenu = false }
                    ) {

                        // "About" item
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.about)) },
                            onClick = {
                                Toast.makeText(
                                    context,
                                    R.string.scaffold_app,
                                    Toast.LENGTH_SHORT
                                ).show()
                                showPopupMenu = false
                            },
                        )

                        // "Clear" item
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.clear)) },
                            onClick = {
                                itemsRepository.clear()
                                showPopupMenu = false
                            },
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (navigationState.currentRoute == AppRoute.Tab.Items) {
                // floating action button is displayed only for 1 screen - Route.Tab.Items
                FloatingActionButton(
                    onClick = {
                        router.launch(AppRoute.AddItem)
                    }

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_new_item)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            // bottom navigation bar is hidden if more than one
            // screen is located in the back stack:
            if (navigationState.isRoot) {
                NavigationBar {
                    // render NavigationBarItem for each tab route:
                    RootTabs.forEach { tab ->
                        NavigationBarItem(
                            selected = navigationState.currentRoute == tab,
                            label = { Text(stringResource(tab.titleRes)) },
                            onClick = {
                                router.restart(tab)
                            },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = stringResource(tab.titleRes)
                                )
                            }
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        NavigationHost(
            navigation = navigation,
            modifier = Modifier.padding(paddingValues)
        ) { currentRoute ->
            // screen content:
            when (currentRoute) {
                AppRoute.Tab.Items -> ItemsScreen()
                AppRoute.Tab.Settings -> SettingsScreen()
                AppRoute.Tab.Profile -> ProfileScreen()
                AppRoute.AddItem -> AddItemScreen()

            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun AppScreenPreview() {
    AppTheme(useDarkTheme = false) {
        AppScaffold()
    }
}