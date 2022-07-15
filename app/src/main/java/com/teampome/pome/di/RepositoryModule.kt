package com.teampome.pome.di

import com.teampome.pome.data.repository.AuthRepository
import com.teampome.pome.data.repository.MockRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(authRepository: MockRepositoryImpl): AuthRepository = authRepository
}