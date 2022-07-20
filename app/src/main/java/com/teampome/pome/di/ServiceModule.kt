package com.teampome.pome.di

import com.teampome.pome.data.FriendService
import com.teampome.pome.data.GoalService
import com.teampome.pome.data.RemindService
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

    @Provides
    @Singleton
    fun provideGoalService(retrofit: Retrofit): GoalService =
        retrofit.create(GoalService::class.java)

    @Provides
    @Singleton
    fun provideRemindService(retrofit: Retrofit): RemindService=
        retrofit.create(RemindService::class.java)
}
