package project.collab.banksampah.presentation.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

val navItems = listOf(
    BottomNavRoute(
        name = "Beranda",
        route = NavRoute.Home.Lobby,
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    BottomNavRoute(
        name = "Galeri",
        route = NavRoute.Home.Gallery,
        icon = Icons.Outlined.Image,
        selectedIcon = Icons.Filled.Image
    ),
    BottomNavRoute(
        name = "Kategori",
        route = NavRoute.Home.TypeTrash,
        icon = Icons.AutoMirrored.Outlined.ListAlt,
        selectedIcon = Icons.AutoMirrored.Filled.ListAlt
    ),
    BottomNavRoute(
        name = "Jadwal",
        route = NavRoute.Home.Schedule,
        icon = Icons.Outlined.CalendarMonth,
        selectedIcon = Icons.Filled.CalendarMonth
    ),
    BottomNavRoute(
        name = "Profil",
        route = NavRoute.Home.Profile,
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person
    )
)