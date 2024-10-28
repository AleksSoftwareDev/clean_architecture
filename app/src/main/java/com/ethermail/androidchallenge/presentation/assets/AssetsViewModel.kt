package com.ethermail.androidchallenge.presentation.assets

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ethermail.androidchallenge.domain.usecase.assets.GetAssetsUseCase
import com.ethermail.androidchallenge.presentation.assets.state.AssetsStates
import com.ethermail.androidchallenge.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AssetsViewModel @Inject constructor(
    private val getAssetsUseCase: GetAssetsUseCase,
) : BaseViewModel<AssetsStates>() {


    override fun initUIState(): AssetsStates {
        return AssetsStates.Empty
    }

    fun getAssetsApiCall() {
        getAssetsUseCase.execute(Unit)
            .onEach { assetsList ->
                _uiState.value =
                    assetsList?.let { AssetsStates.Success(assetsList) } ?: AssetsStates.Empty
            }
            .catch { e ->
                _uiState.value = AssetsStates.Fail
                Log.e(TAG, "Error fetching market data: ${e.localizedMessage}", e)

            }
            .launchIn(viewModelScope)
    }


    companion object {
        private const val TAG = "MarketViewModel"
    }
}
