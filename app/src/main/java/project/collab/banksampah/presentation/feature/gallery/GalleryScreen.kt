package project.collab.banksampah.presentation.feature.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.compose.viewmodel.koinViewModel
import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.gallery.component.GalleryCard
import project.collab.banksampah.presentation.feature.gallery.component.GalleryFullscreenDialog
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_50
import project.collab.banksampah.presentation.utils.handlePagingAppendState
import project.collab.banksampah.presentation.utils.handlePagingState
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.show

@Composable
fun GalleryScreen(
    galleryViewModel: GalleryViewModel = koinViewModel()
) {
    val patternHeights = listOf(220.dp, 280.dp, 200.dp, 260.dp)

    val galleries = galleryViewModel.galleries.collectAsLazyPagingItems()

    var selectedImageGallery by remember { mutableStateOf<Gallery?>(null) }
    val galleryFullScreenState = rememberVisibilityState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Galeri Kegiatan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = Spacing_30, end = Spacing_30, top = Spacing_30, bottom = Spacing_20)
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = Spacing_10)
                .fillMaxWidth(),
            color = PrimaryGreen
        )

        Spacer(modifier = Modifier.size(Spacing_30))

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(Spacing_12),
            verticalItemSpacing = Spacing_12,
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp, top = 4.dp, bottom = 88.dp
            )
        ) {
            handlePagingState(
                items = galleries,
                onLoading = {
                    items(count = 5) { index ->
                        val height = patternHeights[index % patternHeights.size]
                        BaseShimmer(
                            modifier = Modifier
                                .height(height)
                                .fillMaxWidth()
                        )
                    }
                },
                onSuccess = {
                    items(
                        count = galleries.itemCount,
                        key = { index ->
                            val gallery = galleries[index]
                            if (gallery != null) {
                                "gallery_${gallery.id}_$index"
                            } else {
                                "null_$index"
                            }
                        }
                    ) { index ->
                        val height = patternHeights[index % patternHeights.size]
                        galleries[index]?.let { gallery ->
                            GalleryCard(
                                item = gallery,
                                height = height,
                                onClick = {
                                    selectedImageGallery = gallery
                                    galleryFullScreenState.show()
                                }
                            )
                        }
                    }

                    handlePagingAppendState(
                        items = galleries,
                        onLoading = {
                            item {
                                BaseShimmer(
                                    modifier = Modifier
                                        .padding(horizontal = Spacing_10)
                                        .height(Size_100)
                                        .fillMaxWidth()
                                )
                            }
                        },
                        onError = {
                            item { Text("Terjadi error saat load awal") }
                        },
                        onNotLoading = {}
                    )
                },
                onError = {}
            )
        }
    }

    selectedImageGallery?.let { gallery ->
        GalleryFullscreenDialog(
            gallery = gallery,
            isVisible = galleryFullScreenState.value,
            onDismiss = {
                selectedImageGallery = null
                galleryFullScreenState.hide()
            },
        )
    }
}