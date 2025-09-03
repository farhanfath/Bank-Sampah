package project.collab.banksampah.presentation.components.base


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle(
        color = PrimaryGreen
    ) },
    shape: Shape = RoundedCornerShape(topStart = Spacing_30, topEnd = Spacing_30),
    containerColor: Color = Color.White,
    isFullExpand: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = isFullExpand
    )

    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            modifier = modifier,
            sheetState = sheetState,
            dragHandle = dragHandle,
            shape = shape,
            containerColor = containerColor,
            content = content
        )
    }
}

/**
 * extension for bottom sheet
 */
@Composable
fun rememberVisibilityState(): MutableState<Boolean> {
    return remember { mutableStateOf(false) }
}