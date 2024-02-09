package com.kbcoding.l19_constraintlayout.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kbcoding.l19_constraintlayout.Square

@Composable
@Preview(showSystemUi = true)
fun Example02Center() {
    ConstraintLayout {

        Square(
            color = Color.Red,
            modifier = Modifier.constrainAs(createRef()) {
                centerVerticallyTo(parent)
            }
        )
        Square(
            color = Color.Blue,
            modifier = Modifier.constrainAs(createRef()) {
                centerHorizontallyTo(parent)
            }
        )

    }
}

