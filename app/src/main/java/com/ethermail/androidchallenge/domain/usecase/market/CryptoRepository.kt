package com.ethermail.androidchallenge.domain.usecase.market

import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.data.model.markets.MarketData
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    fun getMarketApiCall(): Flow<List<MarketData>?>
    fun getAssetsApiCall(): Flow<List<AssetData>?>
}