package project.collab.banksampah.presentation.feature.profile.user.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_3
import project.collab.banksampah.presentation.theme.Size_32
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun ProfileSettingsSection(
    onSettingClick: () -> Unit,
    onChangePassClick: () -> Unit,
    onFaqClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = Spacing_12)
    ) {
        item {
            Text(
                text = "Akun Setting",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = Spacing_12),
                thickness = Size_3,
                color = PrimaryGreen
            )
        }

        item {
            ProfileSettingItem(
                icon = R.drawable.ic_setting_user,
                title = "Setting Pengguna",
                onClick = onSettingClick
            )
        }

        item {
            ProfileSettingItem(
                icon = R.drawable.ic_password,
                title = "Ganti Password",
                onClick = onChangePassClick
            )
        }

        item {
            ProfileSettingItem(
                icon = R.drawable.ic_faq,
                title = "FAQs",
                onClick = onFaqClick
            )
        }

    }
}

@Composable
fun ProfileSettingItem(
    @DrawableRes icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .padding(vertical = Spacing_12, horizontal = Spacing_8)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BaseImage(
                modifier = Modifier.size(Size_32),
                image = icon,
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = Spacing_12)
                    .weight(1f),
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "",
                tint = PrimaryGreen
            )
        }
        HorizontalDivider(
            thickness = Size_3,
            color = PrimaryGreen
        )
    }
}