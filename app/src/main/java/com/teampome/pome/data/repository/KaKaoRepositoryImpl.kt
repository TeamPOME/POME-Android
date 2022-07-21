package com.teampome.pome.data.repository


import com.teampome.pome.data.local.PomeDataStore
import com.teampome.pome.data.service.AuthService
import retrofit2.await
import timber.log.Timber
import javax.inject.Inject

/*
class KaKaoRepositoryImpl @Inject constructor(
    private val dataStore: PomeDataStore,
    private val service: AuthService
) : KaKaoRepository {
    override suspend fun login(social: String,token: String) {
        runCatching {
            service.login("kakao", token)
        }.fold({
            with(dataStore) {
                userToken = token
            }
        }, Timber::e)
    }
}
*/
