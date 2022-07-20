package com.teampome.pome.di

import com.teampome.pome.data.FriendService
import com.teampome.pome.data.GoalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideFriendsService(retrofit: Retrofit):FriendService = 
        retrofit.create(FriendService::class.java)

    fun provideGoalService(retrofit: Retrofit): GoalService =
        retrofit.create(GoalService::class.java)
}