package project.collab.banksampah.data.remote.model.response.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
	val message: String,
	val status: Boolean,
	val token: String
)
