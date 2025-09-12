package project.collab.banksampah.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import project.collab.banksampah.domain.model.LoginInfo

class TokenDataSource (val dataStore: DataStore<Preferences>) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val USER_PHONE_NUMBER_KEY = stringPreferencesKey("user_phone_number")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    suspend fun saveUserLoginData(data: LoginInfo) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = data.token
            preferences[USER_PHONE_NUMBER_KEY] = data.phoneNumber
            preferences[USER_ID_KEY] = data.userId
        }
    }

    suspend fun getAccessToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[ACCESS_TOKEN_KEY]
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY] ?: ""
        }
    }

    suspend fun getUserPhoneNumber(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_PHONE_NUMBER_KEY]
    }

    suspend fun getUserId(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_ID_KEY]
    }


    fun isLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY] != null
        }
    }

    suspend fun clearUserLoginData() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(USER_PHONE_NUMBER_KEY)
            preferences.remove(USER_ID_KEY)
        }
    }
}