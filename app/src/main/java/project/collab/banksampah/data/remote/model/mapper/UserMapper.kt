package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.user.UserData
import project.collab.banksampah.domain.model.User

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