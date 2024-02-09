package com.kbcoding.l19_constraintlayout.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kbcoding.l19_constraintlayout.Square

@Composable
@Preview(showSystemUi = true)
fun Example03LinksToParent() {
    ConstraintLayout {

        Square(
            color = Color.Red,
            modifier = Modifier
                .constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Square(
            color = Color.Blue,
            modifier = Modifier
                .constrainAs(createRef()) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )

        Square(
            color = Color.Cyan,
            modifier = Modifier
                .constrainAs(createRef()) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        )

        Square(
            color = Color.Green,
            modifier = Modifier
                .constrainAs(createRef()) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

