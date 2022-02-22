package me.gumify.hiper.util

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class Tart(private val context: Context, name: String) {
    private val Context.dataStore by preferencesDataStore(
        name = name,
        produceMigrations = { context ->
            listOf(SharedPreferencesMigration(context, name))
        }
    )

    fun getString(key: String): Flow<String> {
        return context.dataStore.data.map { pref ->
            pref[stringPreferencesKey(key)] ?: ""
        }
    }

    fun getInt(key: String): Flow<Int> {
        return context.dataStore.data.map { pref ->
            pref[stringPreferencesKey(key)]?.toInt() ?: 0
        }
    }

    fun getBoolean(key: String): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[stringPreferencesKey(key)].toBoolean()
        }
    }

    fun getFloat(key: String): Flow<Float> {
        return context.dataStore.data.map { pref ->
            pref[stringPreferencesKey(key)]?.toFloat() ?: 0f
        }
    }

    suspend fun put(key: String, value: Any) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value.toString()
        }
    }
}