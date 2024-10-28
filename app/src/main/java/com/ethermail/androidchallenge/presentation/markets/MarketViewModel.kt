package com.ethermail.androidchallenge.presentation.markets

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ethermail.androidchallenge.domain.usecase.market.GetMarketUseCaseById
import com.ethermail.androidchallenge.presentation.markets.state.MarketUIState
import com.ethermail.androidchallenge.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val getMarketUseCase: GetMarketUseCaseById
) : BaseViewModel<MarketUIState>() {


    override fun initUIState(): MarketUIState {
        return MarketUIState.Empty
    }

    fun getMarketApiCall(marketId: String) {
        getMarketUseCase.execute(marketId)
            .onEach { market ->
                _uiState.value = market?.let { MarketUIState.Success(it) } ?: MarketUIState.MarketNoExists
            }
            .catch { e ->
                _uiState.value = MarketUIState.Fail
                Log.e(TAG, "Error fetching market data: ${e.localizedMessage}", e)

            }
            .launchIn(viewModelScope)
    }

    companion object {
        private const val TAG = "MarketViewModel"
    }
}