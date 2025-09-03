package project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.base.BaseBottomSheet
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.PrimaryRed
import project.collab.banksampah.presentation.theme.Size_2
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImageBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit
) {
    BaseBottomSheet(
        isVisible = isVisible,
        onDismiss = onDismiss
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Edit Gambar Profile",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.size(Spacing_16))

            Card(
                modifier = Modifier
                    .padding(horizontal = Spacing_30)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(Spacing_20),
                colors = CardDefaults.cardColors(containerColor = PrimaryGreen)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = Spacing_16, horizontal = Spacing_30)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Ambil Foto",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White
                        )
                    )

                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = Spacing_16)
                        .fillMaxWidth(),
                    thickness = Size_2,
                    color = Color.White
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = Spacing_16, horizontal = Spacing_30)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Ambil di Galeri",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White
                        )
                    )

                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.size(Spacing_16))

            Row(
                modifier = Modifier
                    .padding(horizontal = Spacing_30)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseButton(
                    text = "Hapus Foto",
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    shape = RoundedCornerShape(Spacing_20),
                    containerColor = PrimaryRed
                ) {

                }

                BaseButton(
                    text = "Simpan",
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    shape = RoundedCornerShape(Spacing_20),
                ) {

                }
            }

            Spacer(modifier = Modifier.size(Spacing_20))
        }
    }
}

@Preview
@Composable
private fun PreviewProfileImageBottomSheet() {
    ProfileImageBottomSheet(
        isVisible = true,
        onDismiss = {}
    )
}