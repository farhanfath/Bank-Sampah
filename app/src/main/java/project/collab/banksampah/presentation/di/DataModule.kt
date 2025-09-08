package project.collab.banksampah.presentation.di

import android.content.Context
import androidx.datastore.core.*
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import project.collab.banksampah.data.local.TokenDataSource
import project.collab.banksampah.data.remote.api.AuthApiService
import project.collab.banksampah.data.remote.api.AuthApiServiceImpl
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
}