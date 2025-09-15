package project.collab.banksampah.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import project.collab.banksampah.presentation.feature.article_lobby.ArticleViewModel
import project.collab.banksampah.presentation.feature.auth.login.LoginViewModel
import project.collab.banksampah.presentation.feature.auth.register.RegisterViewModel
import project.collab.banksampah.presentation.feature.gallery.GalleryViewModel
import project.collab.banksampah.presentation.feature.profile.ExchangeViewModel
import project.collab.banksampah.presentation.feature.profile.user.UserViewModel
import project.collab.banksampah.presentation.feature.schedule.ScheduleViewModel
import project.collab.banksampah.presentation.feature.typetrash.TypeOfTrashViewModel

val viewModelModule = module {
    viewModel { LoginViewModel(authUseCase = get()) }
    viewModel { RegisterViewModel(authUseCase = get(), bsuUseCase = get()) }

    viewModel { ArticleViewModel(articleUseCase = get()) }
    viewModel { UserViewModel(get(),get()) }
    viewModel { GalleryViewModel(galleryUseCase = get()) }
    viewModel { TypeOfTrashViewModel(typeOfTrashUseCase = get()) }
    viewModel { ScheduleViewModel(scheduleUseCase = get()) }
    viewModel { ExchangeViewModel(exchangeUseCase = get()) }
}