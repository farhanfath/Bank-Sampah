package project.collab.banksampah.domain.model.response.bsu

import project.collab.banksampah.domain.model.PaginationInfo

data class BsuListResult(
    val pagination: PaginationInfo,
    val bsu: List<Bsu>
)

data class Bsu(
    val id: String,
    val address: String,
    val bsuBranchCode: String,
    val bsuBranchName: String,
    val editor: String,
    val mapAddress: String,
    val timestamp: String
)