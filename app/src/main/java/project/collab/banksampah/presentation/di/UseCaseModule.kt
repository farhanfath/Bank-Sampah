package project.collab.banksampah.presentation.di

import org.koin.dsl.module
import project.collab.banksampah.data.repository.ArticleRepositoryImpl
import project.collab.banksampah.data.repository.AuthRepositoryImpl
import project.collab.banksampah.data.repository.GalleryRepositoryImpl
import project.collab.banksampah.data.repository.UserRepositoryImpl
import project.collab.banksampah.domain.repository.ArticleRepository
import project.collab.banksampah.domain.repository.AuthRepository
import project.collab.banksampah.domain.repository.GalleryRepository
import project.collab.banksampah.domain.repository.UserRepository
import project.collab.banksampah.domain.usecase.ArticleUseCase
import project.collab.banksampah.domain.usecase.ArticleUseCaseImpl
import project.collab.banksampah.domain.usecase.AuthUseCase
import project.collab.banksampah.domain.usecase.AuthUseCaseImpl
import project.collab.banksampah.domain.usecase.GalleryUseCase
import project.collab.banksampah.domain.usecase.GalleryUserCaseImpl
import project.collab.banksampah.domain.usecase.TypeOfTrashUseCase
import project.collab.banksampah.domain.usecase.TypeOfTrashUseCaseImpl
import project.collab.banksampah.domain.usecase.UserUseCase
import project.collab.banksampah.domain.usecase.UserUseCaseImpl

val useCaseModule = module {
    factory<AuthUseCase> {
        AuthUseCaseImpl(authRepository = get())
    }

    factory<ArticleUseCase> {
        ArticleUseCaseImpl(articleRepository = get())
    }

    factory<UserUseCase> {
        UserUseCaseImpl(userRepository = get())
    }

    factory<GalleryUseCase> {
        GalleryUserCaseImpl(galleryRepository = get())
    }

    factory<TypeOfTrashUseCase> {
        TypeOfTrashUseCaseImpl(typeOfTrashRepository = get())
    }
}