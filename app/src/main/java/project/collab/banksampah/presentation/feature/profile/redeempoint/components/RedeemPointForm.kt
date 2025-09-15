package project.collab.banksampah.presentation.feature.profile.redeempoint.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseTextField
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.theme.Size_1
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun RedeemPointForm(
    redeemPointRequest: RedeemPointRequest,
    onDataChange: (RedeemPointRequest) -> Unit,
    onSubmit: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    BaseTitleSection(
        title = "Form"
    )

    Spacer(modifier = Modifier.size(Spacing_10))

    BaseCard(
        modifier = Modifier.fillMaxWidth(),
        stroke = Size_1
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = Spacing_16, horizontal = Spacing_10),
            verticalArrangement = Arrangement.spacedBy(Spacing_4),
        ) {
            Text(
                text = "Masukan Poin",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            BaseTextField(
                hint = "Masukan Jumlah Poin",
                value = if (redeemPointRequest.pointToRedeem == 0) "" else redeemPointRequest.pointToRedeem.toString(),
                onValueChange = { input ->
                    val parsedValue = when {
                        input.isEmpty() -> 0
                        input.all { it.isDigit() } -> {
                            try {
                                input.toInt()
                            } catch (e: NumberFormatException) {
                                0
                            }
                        }

                        else -> redeemPointRequest.pointToRedeem
                    }
                    onDataChange(redeemPointRequest.copy(pointToRedeem = parsedValue))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            Spacer(modifier = Modifier.size(Spacing_16))

            BaseButton(
                modifier = Modifier.align(Alignment.End),
                text = "Submit",
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                onClick = onSubmit
            )
        }
    }
}