package com.ethermail.androidchallenge.presentation.markets.state

import com.ethermail.androidchallenge.presentation.markets.MarketUiItem
import com.ethermail.androidchallenge.ui.base.UiState

sealed interface MarketUIState : UiState {

    data object Empty : MarketUIState

    data object MarketNoExists: MarketUIState

    data class Success(val marketData: MarketUiItem) : MarketUIState

    data object Fail : MarketUIState
}