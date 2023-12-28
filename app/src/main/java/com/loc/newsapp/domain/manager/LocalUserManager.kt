package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

//TODO when click get started in the splash screen
interface LocalUserManager {

   //save the news using this function.
   suspend fun saveAppEntry()

//   A function that returns a Flow<Boolean>. Flows are part of Kotlin's coroutine API and are used for asynchronous and reactive programming.
   //this function return the where it is stored or not.
   fun readAppEntry(): Flow<Boolean>
}