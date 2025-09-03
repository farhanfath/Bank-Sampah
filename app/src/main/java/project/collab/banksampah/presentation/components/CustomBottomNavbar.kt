package project.collab.banksampah.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import project.collab.banksampah.presentation.navigation.route.BottomNavRoute
import project.collab.banksampah.presentation.navigation.route.NavRoute
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_40
import project.collab.banksampah.presentation.theme.Size_80
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun CustomBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing_12, vertical = Spacing_12)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing_10)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(Size_80),
                shape = RoundedCornerShape(Size_40),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = Spacing_8
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Spacing_10),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    mainNavItems.forEach { navItem ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.hasRoute(navItem.route::class)
                        } == true

                        NavigationItem(
                            navItem = navItem,
                            isSelected = isSelected,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }

            // forum icon
            Box {
                val isForumSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(NavRoute.Home.Forum::class)
                } == true

                Card(
                    modifier = Modifier
                        .size(64.dp)
                        .clickable {
                            navController.navigate(NavRoute.Home.Forum) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = if (isForumSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isForumSelected)
                                Icons.AutoMirrored.Filled.Chat
                            else
                                Icons.AutoMirrored.Outlined.Chat,
                            contentDescription = "Forum",
                            tint = if (isForumSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationItem(
    navItem: BottomNavRoute<*>,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon with circular background when selected
        Box(
            modifier = Modifier
                .size(Size_40),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = PrimaryGreen
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = navItem.selectedIcon,
                            contentDescription = navItem.name,
                            tint = Color.White, // White icon on green background
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            } else {
                Icon(
                    imageVector = navItem.icon,
                    contentDescription = navItem.name,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = navItem.name,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) PrimaryGreen else MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

val mainNavItems = listOf(
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
        name = "Jenis\nSampah",
        route = NavRoute.Home.TypeTrash,
        icon = Icons.AutoMirrored.Outlined.ListAlt,
        selectedIcon = Icons.AutoMirrored.Filled.ListAlt
    ),
    BottomNavRoute(
        name = "Jadwal\nPenukaran",
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