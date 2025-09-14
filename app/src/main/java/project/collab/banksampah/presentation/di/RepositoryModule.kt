package project.collab.banksampah.presentation.di

import org.koin.dsl.module
import project.collab.banksampah.data.repository.*
import project.collab.banksampah.domain.repository.*

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

    single<BsuRepository> {
        BsuRepositoryImpl(apiService = get())
    }

    single<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }
}