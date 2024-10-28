package com.ethermail.androidchallenge.presentation.markets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ethermail.androidchallenge.R
import com.ethermail.androidchallenge.presentation.component.MessageComponent
import com.ethermail.androidchallenge.presentation.component.ProgressComponent
import com.ethermail.androidchallenge.presentation.markets.state.MarketUIState

@Composable
fun MarketScreen(
    marketId: String,
    viewModel: MarketViewModel = hiltViewModel(),
) {
    val uiStateMarket by remember { viewModel.uiState }.collectAsState()
    when (uiStateMarket) {
        is MarketUIState.Empty -> {
            ProgressComponent()
            viewModel.getMarketApiCall(marketId)
        }

        is MarketUIState.Success -> {
            ShowMarket((uiStateMarket as MarketUIState.Success).marketData)
        }

        MarketUIState.Fail -> {
            MessageComponent(stringResource(R.string.error_data))
        }

        MarketUIState.MarketNoExists ->
            MessageComponent(stringResource(R.string.unavailable_market))
    }
}

@Composable
fun ShowMarket(market: MarketUiItem) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .fillMaxSize(0.9F)
                .align(Alignment.Center)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Exchange ID", fontWeight = FontWeight.Bold)
                Text(text = "${market.exchangeId}")

                Text(text = "Rank", fontWeight = FontWeight.Bold)
                Text(text = "${market.rank}")

                Text(text = "Price", fontWeight = FontWeight.Bold)
                Text(text = "${market.priceUsd}")

                Text(text = "Date", fontWeight = FontWeight.Bold)
                Text(text = "${market.updated}")
            }
        }
    }
}

@Composable
@Preview
private fun PreviewMarketScreen() = MarketScreen(
    marketId = "id"
)
