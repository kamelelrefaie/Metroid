package com.example.metroid.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.metroid.model.local.UserData
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "user"


class DataStoreManager(val context: Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)


    companion object {
        val NAME = stringPreferencesKey("NAME")
        val ID = longPreferencesKey("ID")
    }

    suspend fun save(userData: UserData) {
        context.dataStore.edit {
            it[NAME] = userData.name
            it[ID] = userData.id
        }
    }


    suspend fun getFromDataStore() = context.dataStore.data.map {
        UserData(
            name = it[NAME] ?: "",
            id = it[ID] ?: -1L
        )
    }

}