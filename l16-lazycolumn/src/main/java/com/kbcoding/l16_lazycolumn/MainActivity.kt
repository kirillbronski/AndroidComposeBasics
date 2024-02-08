package com.kbcoding.l16_lazycolumn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.l16_lazycolumn.model.UsersService
import com.kbcoding.l16_lazycolumn.ui.UserCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppScreen(usersService: UsersService = UsersService.get()) {
    val userList by usersService.getUsers()
        .collectAsStateWithLifecycle()
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        items(
            items = userList,
            key = { it.id },
        ) { user ->
            UserCard(
                user = user,
                onUserClicked = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.clicked_on, user.name),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onUserDeleted = {
                    usersService.removeUser(user)
                },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}