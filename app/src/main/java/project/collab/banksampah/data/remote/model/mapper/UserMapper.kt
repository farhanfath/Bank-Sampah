package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.request.EditUserRequestDto
import project.collab.banksampah.data.remote.model.request.EditPassRequestDto
import project.collab.banksampah.data.remote.model.response.user.EditUserResponseDto
import project.collab.banksampah.data.remote.model.response.user.EditPassResponseDto
import project.collab.banksampah.data.remote.model.response.user.UserData
import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.model.request.EditPassRequest
import project.collab.banksampah.domain.model.request.UserRequest
import project.collab.banksampah.domain.model.response.edit.EditPassResponse
import project.collab.banksampah.domain.model.response.edit.EditUserResponse
import project.collab.banksampah.presentation.utils.replaceIfNull

fun UserData.toDomain(): User {
    return User(
        userId = id,
        name = name,
        number = number,
        nik = nik,
        address = address,
        bsuBranchName = bsuBranchName,
        bsuBranchCode = bsuBranchCode,
        totalPointUser = totalPointUser,
        currentPointUser = currentPointUser,
        pointUserAlreadyRedeem = pointUserAlreadyRedeem,
        timeStamp = timeStamp
    )
}

fun UserRequest.toDto() : EditUserRequestDto {
    return EditUserRequestDto(
        name = name,
        nik = nik,
        address = address
    )
}

fun EditUserResponseDto.toDomain() : EditUserResponse {
    return EditUserResponse(
        message = message.replaceIfNull(),
        status = status.replaceIfNull()
    )
}

fun EditPassRequest.toDto() : EditPassRequestDto {
    return EditPassRequestDto(
        password = oldPassword,
        newPassword = newPassword
    )
}

fun EditPassResponseDto.toDomain() : EditPassResponse {
    return EditPassResponse(
        message = message.replaceIfNull(),
        status = status.replaceIfNull(),
        userName = user?.name.replaceIfNull()
    )
}