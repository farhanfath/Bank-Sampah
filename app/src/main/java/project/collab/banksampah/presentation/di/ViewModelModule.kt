package project.collab.banksampah.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import project.collab.banksampah.presentation.feature.article_lobby.ArticleViewModel
import project.collab.banksampah.presentation.feature.auth.login.LoginViewModel
import project.collab.banksampah.presentation.feature.auth.register.RegisterViewModel
import project.collab.banksampah.presentation.feature.gallery.GalleryViewModel
import project.collab.banksampah.presentation.feature.profile.user.UserViewModel

val viewModelModule = module {
    viewModel { LoginViewModel(authUseCase = get()) }
    viewModel { RegisterViewModel(authUseCase = get()) }

    viewModel { ArticleViewModel(articleUseCase = get()) }
    viewModel { UserViewModel(get(),get()) }
    viewModel { GalleryViewModel(galleryUseCase = get()) }
}