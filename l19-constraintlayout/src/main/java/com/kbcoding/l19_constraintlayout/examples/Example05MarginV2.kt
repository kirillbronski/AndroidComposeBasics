package com.kbcoding.l19_constraintlayout.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kbcoding.l19_constraintlayout.Square

@Composable
@Preview(showSystemUi = true)
fun Example05MarginV2() {
    ConstraintLayout {

        Square(
            modifier = Modifier
                .constrainAs(createRef()) {
                    start.linkTo(parent.start, margin = 20.dp)
                    top.linkTo(parent.top, margin = 20.dp)
                }
        )

        Square(
            modifier = Modifier
                .constrainAs(createRef()) {
                    end.linkTo(parent.end, margin = 20.dp)
                    top.linkTo(parent.top, margin = 20.dp)
                }
        )

        Square(
            modifier = Modifier
                .constrainAs(createRef()) {
                    start.linkTo(parent.start, margin = 20.dp)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                }
        )

        Square(
            modifier = Modifier
                .constrainAs(createRef()) {
                    end.linkTo(parent.end, margin = 20.dp)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                }
        )
    }
}

