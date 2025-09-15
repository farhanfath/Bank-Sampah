package project.collab.banksampah.presentation.feature.profile.redeempoint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.components.LoadingOverlay
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.profile.ExchangeViewModel
import project.collab.banksampah.presentation.feature.profile.redeempoint.components.RedeemPointForm
import project.collab.banksampah.presentation.feature.profile.redeempoint.components.RedeemSuccessDialog
import project.collab.banksampah.presentation.feature.profile.redeempoint.components.TotalPointCard
import project.collab.banksampah.presentation.feature.profile.user.UserViewModel
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.replaceIfNull
import project.collab.banksampah.presentation.utils.show

@Composable
fun RedeemPointScreen(
    userViewModel: UserViewModel = koinViewModel(),
    exchangeViewModel: ExchangeViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val userDataState by userViewModel.userDataState.collectAsStateWithLifecycle()

    var redeemPointData by remember { mutableStateOf(RedeemPointRequest()) }
    val exchangePointState by exchangeViewModel.pointRequestExchangeState.collectAsStateWithLifecycle()
    val successDialogState = rememberVisibilityState()


    LaunchedEffect(exchangePointState.isRequestExchangeSuccess) {
        if (exchangePointState.isRequestExchangeSuccess) {
            successDialogState.show()
        }
    }

    LaunchedEffect(Unit) {
        userViewModel.getUserData()
    }

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) {
        LoadingOverlay(
            isVisible = exchangePointState.isLoading
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = Spacing_16, vertical = it.calculateTopPadding())
                    .fillMaxSize(),
            ) {
                BaseHeader(
                    title = "Penukaran Poin",
                    onBackClick = onBackClick
                )

                TotalPointCard(
                    totalPoint = userDataState.userData?.totalPointUser.replaceIfNull()
                )

                Spacer(modifier = Modifier.size(Spacing_20))

                RedeemPointForm(
                    redeemPointRequest = redeemPointData,
                    onDataChange = { request ->
                        redeemPointData = request
                    },
                    onSubmit = {
                        exchangeViewModel.requestPointExchange(redeemPointData)
                    }
                )
            }
        }

        exchangePointState.data?.let { data ->
            RedeemSuccessDialog(
                redeemResponse = data,
                isVisible = successDialogState.value,
                onDismiss = successDialogState::hide
            )
        }
    }
}

