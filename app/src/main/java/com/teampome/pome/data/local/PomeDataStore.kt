package com.teampome.pome.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.teampome.pome.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

const val POME_NAME = "POME"

class PomeDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore: SharedPreferences =
        if (BuildConfig.DEBUG) context.getSharedPreferences(POME_NAME, Context.MODE_PRIVATE)
        else EncryptedSharedPreferences.create(
            POME_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    var userToken: String
        set(value) = dataStore.edit { putString("USER_TOKEN", value) }
        get() = dataStore.getString("USER_TOKEN", "") ?: ""

    var refreshToken: String
        set(value) = dataStore.edit { putString("REFRESH_TOKEN", value) }
        get() = dataStore.getString("REFRESH_TOKEN", "") ?: ""

    var nickname: String
        set(value) = dataStore.edit { putString("NICKNAME", value) }
        get() = dataStore.getString("NICKNAME", "") ?: ""
}
