package com.ethermail.androidchallenge.domain.mappers

import com.ethermail.androidchallenge.core.toDayMonthYear
import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.data.model.markets.MarketData
import com.ethermail.androidchallenge.presentation.assets.AssetUiItem
import com.ethermail.androidchallenge.presentation.markets.MarketUiItem

object CryptoMapper {

    fun List<AssetData>.toData(): List<AssetUiItem> {
        return map {
            AssetUiItem(
                symbol = it.symbol ?: "",
                name = it.name ?: "",
                price = it.priceUsd ?: ""
            )
        }
    }

    fun MarketData.toData(): MarketUiItem {
        return run {
            MarketUiItem(
                exchangeId = exchangeId,
                rank = rank,
                priceUsd = priceUsd,
                updated = updated?.toDayMonthYear()
            )
        }
    }
}