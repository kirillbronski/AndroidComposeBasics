package com.kbcoding.l16_lazycolumn.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kbcoding.l16_lazycolumn.R
import com.kbcoding.l16_lazycolumn.model.User

@Preview
@Composable
fun UserCard(
    @PreviewParameter(UserPreviewProvider::class) user: User,
    modifier: Modifier = Modifier,
    onUserClicked: (() -> Unit)? = null,
    onUserDeleted: (() -> Unit)? = null,
) {
    Card(
        shape = RoundedCornerShape(size = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = modifier
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = ripple(),
                onClick = { onUserClicked?.invoke() }
            )
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            UserImage(url = user.photoUrl)
            Spacer(modifier = Modifier.width(16.dp))
            UserInfo(user = user)
            DeleteUserButton(onClick = { onUserDeleted?.invoke() })
        }
    }
}

@Composable
private fun UserImage(url: String) {
    val placeholder = rememberVectorPainter(
        image = Icons.Rounded.AccountCircle,
    )
    AsyncImage(
        model = url,
        placeholder = placeholder,
        contentDescription = stringResource(R.string.user_photo),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .aspectRatio(1f / 1f)
            .clip(CircleShape),
    )
}

@Composable
private fun RowScope.UserInfo(user: User) {
    Column(
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = user.name,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = user.status,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun RowScope.DeleteUserButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.align(Alignment.CenterVertically),
    ) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = stringResource(R.string.delete_user),
        )
    }
}

private class UserPreviewProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User> = sequenceOf(
        User(
            id = 1,
            photoUrl = "",
            name = "Gandalf",
            status = "Lorem ipsum"
        ),
        User(
            id = 1,
            photoUrl = "",
            name = "Gandalf the White, the leader of the Fellowship of the Ring",
            status = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales laoreet commodo."
        ),
    )
}
