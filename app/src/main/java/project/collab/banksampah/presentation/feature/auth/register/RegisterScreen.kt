package project.collab.banksampah.presentation.feature.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.compose.viewmodel.koinViewModel
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.components.LoadingOverlay
import project.collab.banksampah.presentation.feature.auth.components.AuthHeader
import project.collab.banksampah.presentation.feature.auth.register.components.RegisterForm

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    onRegisterSuccess: () -> Unit,
    onGoToLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    var registerData by remember { mutableStateOf(RegisterRequest()) }
    val registerState by viewModel.registerState.collectAsStateWithLifecycle()

    val bsuDropdownState by viewModel.dropdownState.collectAsState()
    val bsuPagingItems = viewModel.bsuPagingData.collectAsLazyPagingItems()

    LaunchedEffect(registerState.isRegisterSuccess) {
        if (registerState.isRegisterSuccess) {
            Toast.makeText(context, "${registerState.message}, silahkan lanjutkan untuk login", Toast.LENGTH_SHORT).show()
            onRegisterSuccess()
        }
    }

    LaunchedEffect(registerState.isRegisterFailed) {
        if (registerState.isRegisterFailed) {
            val message = registerState.message
            if (!message.isNullOrBlank()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) { paddingValues ->
        LoadingOverlay(
            isVisible = registerState.isLoading
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                item {
                    AuthHeader(onBackClick = onBackClick)
                }

                item {
                    RegisterForm(
                        registerData = registerData,
                        bsuDropdownState = bsuDropdownState,
                        bsuPagingItems = bsuPagingItems,
                        onDataChange = { registerData = it },
                        onBsuSearchChange = viewModel::updateSearchQuery,
                        onBsuSelect = { bsu ->
                            viewModel.selectBsu(bsu)
                            registerData = registerData.copy(
                                bsuBranchName = bsu.bsuBranchName,
                                bsuBranchCode = bsu.bsuBranchCode
                            )
                        },
                        onBsuDropdownExpandChange = viewModel::updateDropdownExpanded,
                        onRegisterClick = {
                            viewModel.register(registerData)
                        },
                        onGoToLoginClick = onGoToLoginClick
                    )
                }
            }
        }
    }
}