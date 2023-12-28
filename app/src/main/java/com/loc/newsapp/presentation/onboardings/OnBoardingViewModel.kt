package com.loc.newsapp.presentation.onboardings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.UserAppEntryUseCases
import com.loc.newsapp.presentation.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//because it is viewModel inherits the viewModel and @Inject annotation use to inject this datastore class in the constructor
// hilt automatically manage this datastore class i.e., userAppEntryUseCases
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val userAppEntryUseCases: UserAppEntryUseCases
):ViewModel() {

    //create the function for the event.
    fun onEvent(event: OnBoardingEvent){
//        suppose i have a multiple event then use the switch statement.
        when(event){
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }

    }

    private fun saveAppEntry() {
        //call this function in the viewModel scope because when the activity is finished the scope is also
        // finished so this used for the memory leaks. and others.
        viewModelScope.launch {
            userAppEntryUseCases.saveAppEntry
        }
    }
}