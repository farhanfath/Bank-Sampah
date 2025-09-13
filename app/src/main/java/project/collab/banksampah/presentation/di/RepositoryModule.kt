package project.collab.banksampah.presentation.di

import org.koin.dsl.module
import project.collab.banksampah.data.repository.ArticleRepositoryImpl
import project.collab.banksampah.data.repository.AuthRepositoryImpl
import project.collab.banksampah.data.repository.GalleryRepositoryImpl
import project.collab.banksampah.data.repository.TypeOfTrashRepositoryImpl
import project.collab.banksampah.data.repository.UserRepositoryImpl
import project.collab.banksampah.domain.repository.ArticleRepository
import project.collab.banksampah.domain.repository.AuthRepository
import project.collab.banksampah.domain.repository.GalleryRepository
import project.collab.banksampah.domain.repository.TypeOfTrashRepository
import project.collab.banksampah.domain.repository.UserRepository

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(authApiService = get(), tokenDataSource = get())
    }

    single<ArticleRepository> {
        ArticleRepositoryImpl(apiService = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userApiService = get(), tokenDataSource = get())
    }

    single<GalleryRepository> {
        GalleryRepositoryImpl(apiService = get())
    }

    single<TypeOfTrashRepository> {
        TypeOfTrashRepositoryImpl(apiService = get())
    }
}