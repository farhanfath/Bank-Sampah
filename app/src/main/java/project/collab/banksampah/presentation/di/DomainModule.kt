package project.collab.banksampah.presentation.di

import org.koin.dsl.module
import project.collab.banksampah.domain.usecase.AuthUseCase
import project.collab.banksampah.domain.usecase.AuthUseCaseImpl

val domainModule = module {
    factory<AuthUseCase> {
        AuthUseCaseImpl(authRepository = get())
    }

}