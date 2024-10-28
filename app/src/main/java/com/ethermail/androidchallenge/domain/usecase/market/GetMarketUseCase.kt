package com.ethermail.androidchallenge.domain.usecase.market

import com.ethermail.androidchallenge.domain.BaseUseCase
import com.ethermail.androidchallenge.domain.mappers.CryptoMapper.toData
import com.ethermail.androidchallenge.presentation.markets.MarketUiItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMarketUseCaseById @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : BaseUseCase<String, Flow<MarketUiItem?>>() {
    override fun execute(params: String): Flow<MarketUiItem?> {
        return cryptoRepository.getMarketApiCall()
            .map { marketList ->
                marketList
                    ?.filter { it.baseId.equals(params, ignoreCase = true) }
                    ?.maxByOrNull { it.volumeUsd24Hr?.toDoubleOrNull() ?: 0.0 }
                    ?.toData()
            }
    }
}