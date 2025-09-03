package project.collab.banksampah.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import project.collab.banksampah.presentation.feature.profile.user.ProfileUserScreen
import project.collab.banksampah.presentation.navigation.host.AppNavHost
import project.collab.banksampah.presentation.theme.BankSampahTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            BankSampahTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                AppNavHost(
                    navController = navController
                )
            }
        }
    }
}