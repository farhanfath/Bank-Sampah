package project.collab.banksampah.domain.model.response.point_exchange

// response dari post penukaran poin
data class PointExchangeRequestResponse(
    val message: String,
    val id: String,
    val acceptDate: String,
    val description: String,
    val editor: String,
    val pointExchangeAccepted: String,
    val pointExchangeRequest: Int,
    val requestDate: String,
    val timeStamp: String,
    val transactionCode: String,
    val transactionStatus: String,
    val userBranchName: String,
    val userName: String,
)