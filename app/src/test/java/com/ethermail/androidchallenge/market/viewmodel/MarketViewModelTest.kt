package com.ethermail.androidchallenge.market.viewmodel

import com.ethermail.androidchallenge.data.model.markets.MarketData
import com.ethermail.androidchallenge.domain.usecase.market.CryptoRepository
import com.ethermail.androidchallenge.domain.usecase.market.GetMarketUseCaseById
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetMarketUseCaseByIdTest {

    @MockK
    private lateinit var cryptoRepository: CryptoRepository

    private lateinit var useCase: GetMarketUseCaseById

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetMarketUseCaseById(cryptoRepository)
    }

    @Test
    fun `should return market with highest volume`() = runBlocking {
        val marketDataList = listOf(
            MarketData(
                baseId = "maker",
                baseSymbol = "BTC",
                exchangeId = "bibox",
                percentExchangeVolume = 20.0,
                priceQuote = "50000",
                priceUsd = "50000",
                quoteId = "quote1",
                quoteSymbol = "USD",
                rank = "1",
                tradesCount24Hr = null,
                updated = System.currentTimeMillis(),
                volumeUsd24Hr = "7829521703.7471673204845676"
            ),
            MarketData(
                baseId = "maker",
                baseSymbol = "BTC",
                exchangeId = "alterdice",
                percentExchangeVolume = 30.0,
                priceQuote = "51000",
                priceUsd = "51000",
                quoteId = "quote1",
                quoteSymbol = "USD",
                rank = "1",
                tradesCount24Hr = null,
                updated = System.currentTimeMillis(),
                volumeUsd24Hr = "721702.7471673204845676"
            ),
            MarketData(
                baseId = "maker",
                baseSymbol = "ETH",
                exchangeId = "bibox3",
                percentExchangeVolume = 25.0,
                priceQuote = "4000",
                priceUsd = "4000",
                quoteId = "quote2",
                quoteSymbol = "USD",
                rank = "2",
                tradesCount24Hr = null,
                updated = System.currentTimeMillis(),
                volumeUsd24Hr = "7829521702.7471673204845676"
            )
        )
        coEvery { cryptoRepository.getMarketApiCall() } returns flowOf(marketDataList)
        val result = useCase.execute("maker").first()
        assertEquals(result?.exchangeId, "bibox")
    }

    @Test
    fun `should return null when no matching`() = runBlocking {
        val marketDataList = listOf(
            MarketData(
                baseId = "2",
                baseSymbol = "ETH",
                exchangeId = "exchange3",
                percentExchangeVolume = 25.0,
                priceQuote = "4000",
                priceUsd = "4000",
                quoteId = "quote2",
                quoteSymbol = "USD",
                rank = "2",
                tradesCount24Hr = 80,
                updated = System.currentTimeMillis(),
                volumeUsd24Hr = "150.0"
            )
        )
        coEvery { cryptoRepository.getMarketApiCall() } returns flowOf(marketDataList)
        val result = useCase.execute("1").first()
        assertNull(result)
    }

    @Test
    fun `should return null for empty market list`() = runBlocking {
        coEvery { cryptoRepository.getMarketApiCall() } returns flowOf(emptyList())
        val result = useCase.execute("1").first()
        assertNull(result)
    }
}
