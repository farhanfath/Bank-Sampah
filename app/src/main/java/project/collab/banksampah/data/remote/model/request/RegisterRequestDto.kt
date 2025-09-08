package project.collab.banksampah.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
	val bsuBranchName: String?,
	val number: String?,
	val nik: String?,
	val password: String?,
	val address: String?,
	val name: String?,
	val bsuBranchCode: String?
)
