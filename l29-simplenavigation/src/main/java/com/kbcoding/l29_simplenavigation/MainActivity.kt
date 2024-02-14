package com.kbcoding.l29_simplenavigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l29_simplenavigation.ui.theme.AppTheme

sealed class Route(

    /**
     * Screen title will be displayed in the app toolbar and in the bottom
     * navigation bar.
     */
    @StringRes val titleRes: Int

) {

    /**
     * "Add New Item" screen.
     */
    object AddItem : Route(R.string.add_item)

    /**
     * Screens that can be displayed in the bottom navigation bar.
     */
    sealed class Tab(

        @StringRes titleRes: Int,

        /**
         * Additional icon for tabs within bottom navigation bar
         */
        val icon: ImageVector,

        ) : Route(titleRes) {

        object Items : Tab(R.string.items, Icons.Default.List)
        object Settings : Tab(R.string.settings, Icons.Default.Settings)
        object Profile : Tab(R.string.profile, Icons.Default.AccountBox)

    }
}

/**
 * The list of all root tabs.
 */
val RootTabs =
    listOf(Route.Tab.Items, Route.Tab.Settings, Route.Tab.Profile)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(itemsRepository: ItemsRepository = ItemsRepository.get()) {
    val items by itemsRepository.getItems().collectAsStateWithLifecycle()

    // screen back stack
    val stack = remember {
        mutableStateListOf<Route>(Route.Tab.Items)
    }
    // current screen visible to the user
    val currentRoute = stack.last()
    // whether the current screen is a root screen or not
    val isRoot = stack.size == 1

    BackHandler(enabled = !isRoot) {
        // pop screen from the back stack
        stack.removeLast()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(currentRoute.titleRes),
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
                            if (!isRoot) stack.removeLast()
                        }
                    ) {
                        Icon(
                            imageVector = if (isRoot)
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
            if (currentRoute == Route.Tab.Items) {
                // floating action button is displayed only for 1 screen - Route.Tab.Items
                FloatingActionButton(
                    onClick = { stack.add(Route.AddItem) }
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
            if (isRoot) {
                NavigationBar {
                    // render NavigationBarItem for each tab route:
                    RootTabs.forEach { tab ->
                        NavigationBarItem(
                            selected = currentRoute == tab,
                            label = { Text(stringResource(tab.titleRes)) },
                            onClick = {
                                stack.clear()
                                stack.add(tab)
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
        Box(modifier = Modifier.padding(paddingValues)) {
            // screen content:
            when (currentRoute) {
                Route.Tab.Items -> ItemsScreen(items)
                Route.Tab.Settings -> SettingsScreen()
                Route.Tab.Profile -> ProfileScreen()
                Route.AddItem -> {
                    AddItemScreen(
                        onSubmitNewItem = {
                            itemsRepository.addItem(it)
                            stack.removeLast()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemsScreen(items: List<String>) {
    if (items.isEmpty()) {
        Text(
            text = stringResource(R.string.no_items),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize().wrapContentHeight(),
        )
    } else {
        LazyColumn {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(all = 8.dp),
                )
            }
        }
    }
}

@Composable
fun AddItemScreen(onSubmitNewItem: (String) -> Unit) {
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

@Composable
fun SettingsScreen() {
    Text(
        text = "Settings Screen",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
    )
}

@Composable
fun ProfileScreen() {
    Text(
        text = "Profile Screen",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
    )
}

@Composable
@Preview(showSystemUi = true)
fun AppScreenPreview() {
    AppTheme(useDarkTheme = false) {
        AppScreen()
    }
}