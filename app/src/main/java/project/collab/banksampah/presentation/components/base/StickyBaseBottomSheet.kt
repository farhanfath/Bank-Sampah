package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickyBaseBottomSheet(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    dragHandle: @Composable (() -> Unit)? = {
        BottomSheetDefaults.DragHandle(color = PrimaryGreen)
    },
    shape: Shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    containerColor: Color = Color.White,
    isFullExpand: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = isFullExpand
    )

    // Prevent dismissal with LaunchedEffect
    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == SheetValue.Hidden) {
            sheetState.show()
        }
    }

    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = {},
            modifier = modifier,
            sheetState = sheetState,
            dragHandle = dragHandle,
            shape = shape,
            containerColor = containerColor,
            contentWindowInsets = { WindowInsets(0,0,0,0) },
            content = content
        )
    }
}