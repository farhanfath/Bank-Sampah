package project.collab.banksampah.presentation.di

import android.content.Context
import androidx.datastore.core.*
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import project.collab.banksampah.data.local.TokenDataSource
import project.collab.banksampah.data.remote.api.ArticleApiService
import project.collab.banksampah.data.remote.api.ArticleApiServiceImpl
import project.collab.banksampah.data.remote.api.AuthApiService
import project.collab.banksampah.data.remote.api.AuthApiServiceImpl
import project.collab.banksampah.data.remote.api.GalleryApiService
import project.collab.banksampah.data.remote.api.GalleryApiServiceImpl
import project.collab.banksampah.data.remote.api.UserApiService
import project.collab.banksampah.data.remote.api.UserApiServiceImpl
import project.collab.banksampah.data.remote.client.HttpClientFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bank_sampah_prefs")

val dataModule = module {
    single<DataStore<Preferences>> {
        androidContext().dataStore
    }

    single {
        TokenDataSource(dataStore = get())
    }

    single<HttpClient> {
        HttpClientFactory.create(get())
    }

    single<AuthApiService> {
        AuthApiServiceImpl(httpClient = get())
    }

    single<ArticleApiService> {
        ArticleApiServiceImpl(httpClient = get())
    }

    single<UserApiService> {
        UserApiServiceImpl(httpClient = get())
    }

    single<GalleryApiService> {
        GalleryApiServiceImpl(httpClient = get())
    }
}