package com.kbcoding.l35_navigationscreensstate.ui.scaffold

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.kbcoding.l35_navigationscreensstate.ItemsRepository
import com.kbcoding.l35_navigationscreensstate.R
import com.kbcoding.l35_navigationscreensstate.ui.AppRoute
import com.kbcoding.navigationstack.navigation.NavigationState
import com.kbcoding.navigationstack.navigation.Router

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppToolbar(
    navigationState: NavigationState,
    router: Router,
    itemsRepository: ItemsRepository
) {
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
                        Icons.AutoMirrored.Filled.ArrowBack,
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
}