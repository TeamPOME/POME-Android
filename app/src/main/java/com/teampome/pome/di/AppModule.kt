package com.teampome.pome.di

import android.content.Context
import com.teampome.pome.util.FileParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFileParser(
        @ApplicationContext context: Context
    ): FileParser = FileParser(context)

}