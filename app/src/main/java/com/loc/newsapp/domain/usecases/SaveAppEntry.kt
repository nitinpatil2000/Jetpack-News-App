package com.loc.newsapp.domain.usecases

import com.loc.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    //create operator function because i want call this function with their name directly.
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}