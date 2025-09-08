package project.collab.banksampah.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
	val number: String?,
	val password: String?
)
