package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.request.LoginRequestDto
import project.collab.banksampah.data.remote.model.request.RegisterRequestDto
import project.collab.banksampah.data.remote.model.response.auth.LoginResponseDto
import project.collab.banksampah.data.remote.model.response.auth.RegisterResponseDto
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.domain.model.response.auth.LoginResponse
import project.collab.banksampah.domain.model.response.auth.RegisterResponse

fun LoginRequest.toDto() : LoginRequestDto {
    return LoginRequestDto(
        number = phoneNumber,
        password = password
    )
}

fun RegisterRequest.toDto() : RegisterRequestDto {
    return RegisterRequestDto(
        name = name,
        nik = nik,
        number = phoneNumber,
        address = address,
        bsuBranchName = bsuBranchName,
        bsuBranchCode = bsuBranchCode,
        password = password
    )
}

fun RegisterResponseDto.toDomain() : RegisterResponse {
    return RegisterResponse(
        message = message,
        status = status
    )
}

fun LoginResponseDto.toDomain() : LoginResponse {
    return LoginResponse(
        status = status,
        message = message,
        token = token
    )
}