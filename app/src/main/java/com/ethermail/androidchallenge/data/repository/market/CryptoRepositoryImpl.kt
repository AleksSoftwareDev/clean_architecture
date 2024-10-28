package com.ethermail.androidchallenge.data.repository.market

import com.ethermail.androidchallenge.data.datasource.remote.CoincapService
import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.data.model.markets.MarketData
import com.ethermail.androidchallenge.domain.usecase.market.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CryptoRepositoryImpl @Inject constructor(
    private val apiService: CoincapService
) : CryptoRepository {

    override fun getMarketApiCall(): Flow<List<MarketData>?> {
        return flow {
            emit(apiService.getMarkets().marketData)
        }
    }

    override fun getAssetsApiCall(): Flow<List<AssetData>?> {
        return flow {
            emit(apiService.getAssets().assetData)
        }
    }
}