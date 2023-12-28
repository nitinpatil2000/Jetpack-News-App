package com.loc.newsapp.presentation

//todo define the sealed class because pass this event ui to view model.
sealed class OnBoardingEvent {
    object SaveAppEntry:OnBoardingEvent()

}