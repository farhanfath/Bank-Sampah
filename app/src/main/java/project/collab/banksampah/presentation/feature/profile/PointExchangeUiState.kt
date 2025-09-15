package project.collab.banksampah.presentation.feature.profile

import project.collab.banksampah.domain.model.response.point_exchange.PointExchangeRequestResponse

data class PointExchangeUiState(
    val isLoading: Boolean = false,
    val data: PointExchangeRequestResponse? = null,
    val error: String? = null,
    val isRequestExchangeSuccess: Boolean = false,
    val isRequestExchangeFailed: Boolean = false
)