package com.ethermail.androidchallenge.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State : UiState> : ViewModel() {

    protected val _uiState: MutableStateFlow<State> = MutableStateFlow(initUIState())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    abstract fun initUIState(): State
}