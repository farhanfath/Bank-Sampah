package project.collab.banksampah.presentation.feature.auth.register

import project.collab.banksampah.domain.model.response.bsu.Bsu

data class BsuDropdownState(
    val isExpanded: Boolean = false,
    val searchQuery: String = "",
    val selectedBsu: Bsu? = null
)