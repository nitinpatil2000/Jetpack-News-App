package com.loc.newsapp.dependencyinjection

import android.app.Application
import com.loc.newsapp.data.LocalUserManagerImpl
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.domain.usecases.ReadAppEntry
import com.loc.newsapp.domain.usecases.SaveAppEntry
import com.loc.newsapp.domain.usecases.UserAppEntryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//means overall application have the single module so SingletonComponent annotation is provide.
@InstallIn(SingletonComponent::class)
object AppModule {

    //we provide the context so provide application as an argument and inherit the local manager interface and provide their implementation.
    // and provide the context in the constructor.
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    //provide the use cases for the datastore and save the data in the key value pair.
    @Provides
    @Singleton
    fun provideAppEntryUserCases(localUserManager: LocalUserManager) = UserAppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}