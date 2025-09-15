package project.collab.banksampah.presentation.di

import org.koin.dsl.module
import project.collab.banksampah.domain.usecase.*

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

    factory<BsuUseCase> {
        BsuUseCaseImpl(get())
    }

    factory<ScheduleUseCase> {
        ScheduleUseCaseImpl(get())
    }

    factory<ExchangeUseCase> {
        ExchangeUseCaseImpl(get())
    }
}