package com.ethermail.androidchallenge.presentation.assets.state

import com.ethermail.androidchallenge.presentation.assets.AssetUiItem
import com.ethermail.androidchallenge.ui.base.UiState

sealed interface AssetsStates : UiState {

    data object Empty : AssetsStates

    data object Loading : AssetsStates

    data class Success(val assetData: List<AssetUiItem>) : AssetsStates

    data object Fail : AssetsStates
}