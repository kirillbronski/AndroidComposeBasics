package com.kbcoding.l19_constraintlayout.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Visibility
import com.kbcoding.l19_constraintlayout.Square

@Composable
@Preview(showSystemUi = true)
fun Example08VisibilityAndGoneMargin() {
    ConstraintLayout {
        val (
            centerSquare
        ) = createRefs()

        Square(
            size = 50.dp,
            color = Color.Green,
            modifier = Modifier.constrainAs(createRef()) {
                centerHorizontallyTo(centerSquare)
                bottom.linkTo(centerSquare.top)
            }
        )

        Square(
            modifier = Modifier.constrainAs(centerSquare) {
                centerTo(parent)
                visibility = Visibility.Visible
            }
        )

        Square(
            size = 50.dp,
            color = Color.Blue,
            modifier = Modifier.constrainAs(createRef()) {
                centerHorizontallyTo(centerSquare)
                top.linkTo(
                    centerSquare.bottom,
                    margin = 10.dp,
                    goneMargin = 50.dp,
                )
            }
        )
    }
}