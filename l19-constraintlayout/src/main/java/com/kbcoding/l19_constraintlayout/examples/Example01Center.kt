package com.kbcoding.l19_constraintlayout.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.kbcoding.l19_constraintlayout.Square

@Composable
@Preview(showSystemUi = true)
fun Example01Center() {
    ConstraintLayout {
        Square(
            modifier = Modifier.constrainAs(createRef()) {
                centerTo(parent)
            }
        )
    }
}

