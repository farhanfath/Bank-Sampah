package project.collab.banksampah.presentation.feature.profile.redeempoint

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.components.base.BaseTextField
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.feature.profile.redeempoint.state.RedeemPointData
import project.collab.banksampah.presentation.theme.Size_1
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun RedeemPointScreen(
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val redeemPointData by remember { mutableStateOf(RedeemPointData()) }

    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_16, vertical = Spacing_4  )
            .fillMaxSize(),
    ) {
        BaseHeader(
            title = "Penukaran Poin",
            onBackClick = onBackClick
        )

        BaseCard(
            modifier = Modifier.align(Alignment.End),
            onClick = {}
        ) {
            Row(
                modifier = Modifier.padding(
                        vertical = Spacing_12,
                        horizontal = Spacing_12
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(end = Spacing_16)
                ) {
                    Text(
                        text = "Poin Anda : ",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        text = "100.000",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BaseImage(
                        modifier = Modifier.size(Size_30),
                        image = R.drawable.ic_point
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(Spacing_20))

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
                    hint = "Masukan poin yang ingin ditukarkan",
                    value = redeemPointData.pointToRedeem,
                    onValueChange = {
                        redeemPointData.copy(pointToRedeem = it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                Spacer(modifier = Modifier.size(Spacing_10))

                Text(
                    text = "Cabang BSU",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                BaseTextField(
                    hint = "Cabang BSU",
                    value = redeemPointData.cabangBSU,
                    onValueChange = {
                        redeemPointData.copy(cabangBSU = it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
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
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRedeemPointScreen() {
    RedeemPointScreen(
        onBackClick = {}
    )
}

