package project.collab.banksampah.data.remote.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
	val newUser: NewUser,
	val message: String,
	val status: Boolean
)

@Serializable
data class UserNotificationItem(
	val timeStamp: String,
	val notificationTitle: String,
	val description: String,
//	val id: String,
	val statusNotification: String
)

@Serializable
data class NewUser(
	val bsuBranchName: String,
	val address: String,
	val role: String,
	val userNotification: List<UserNotificationItem>,
	val point: Int,
	val timeStamp: String,
	val number: String,
	val nik: String,
	val password: String,
//	val v: Int,
	val name: String,
//	val id: String,
	val bsuBranchCode: String
)
