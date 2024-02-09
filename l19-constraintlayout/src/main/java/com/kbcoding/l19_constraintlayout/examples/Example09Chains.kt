package com.kbcoding.l19_constraintlayout.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.kbcoding.l19_constraintlayout.Rectangle
import com.kbcoding.l19_constraintlayout.Square

@Composable
@Preview(showSystemUi = true)
fun Example09Chains() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (
            square1,
            square2,
            square3,
            square4,
            square5,
        ) = createRefs()
        val blackLine = createRef()

        Square(
            size = 30.dp,
            color = Color.Red,
            modifier = Modifier.constrainAs(square1) {
                centerVerticallyTo(parent)
                horizontalChainWeight = 1f
                //width = Dimension.fillToConstraints
            }
        )
        Square(
            size = 30.dp,
            color = Color.Green,
            modifier = Modifier.constrainAs(square2) {
                centerVerticallyTo(parent)
                horizontalChainWeight = 1f
                //width = Dimension.fillToConstraints
            }
        )
        Square(
            size = 30.dp,
            color = Color.Blue,
            modifier = Modifier.constrainAs(square3) {
                centerVerticallyTo(parent)
                horizontalChainWeight = 1f
                //width = Dimension.fillToConstraints
            }
        )
        Square(
            size = 30.dp,
            color = Color.Cyan,
            modifier = Modifier.constrainAs(square4) {
                centerVerticallyTo(parent)
                horizontalChainWeight = 1f
                //width = Dimension.fillToConstraints
            }
        )
        Square(
            size = 30.dp,
            color = Color.Magenta,
            modifier = Modifier.constrainAs(square5) {
                centerVerticallyTo(parent)
                horizontalChainWeight = 1f
                //width = Dimension.fillToConstraints
            }
        )

        val chain = createHorizontalChain(
            square1, square2, square3, square4, square5,
            chainStyle = ChainStyle.Spread
        )
        constrain(chain) {
            start.linkTo(blackLine.start)
            end.linkTo(blackLine.end)
        }

        Rectangle(
            color = Color.Black,
            modifier = Modifier.constrainAs(blackLine) {
                height = Dimension.value(10.dp)
                width = Dimension.fillToConstraints
                top.linkTo(square1.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 20.dp)
            }
        )
    }
}