package com.ethermail.androidchallenge.presentation.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ethermail.androidchallenge.R
import com.ethermail.androidchallenge.presentation.assets.state.AssetsStates
import com.ethermail.androidchallenge.presentation.assets.state.AssetsStates.Loading
import com.ethermail.androidchallenge.presentation.assets.state.AssetsStates.Success
import com.ethermail.androidchallenge.presentation.component.MessageComponent
import com.ethermail.androidchallenge.presentation.component.ProgressComponent

@Composable
fun AssetsScreen(
    onNavigateToMarket: (String) -> Unit,
    viewModel: AssetsViewModel = hiltViewModel()
) {
    val uiStateAssets by remember { viewModel.uiState }.collectAsState()
    when (uiStateAssets) {
        is AssetsStates.Empty -> {
            ProgressComponent()
            viewModel.run { getAssetsApiCall() }
        }

        is Success -> {
            LIstAssetsView(
                (uiStateAssets as Success).assetData,
                onItemClick = { name ->
                    onNavigateToMarket.invoke(name)
                }
            )
        }

        is Loading -> {
            ProgressComponent()
        }

        AssetsStates.Fail -> {
            MessageComponent(stringResource(R.string.error_data))
        }
    }
}

@Composable
private fun LIstAssetsView(assets: List<AssetUiItem>, onItemClick: (String) -> Unit) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(top = 4.dp),
    ) {
        items(count = assets.size, key = { index -> assets[index].symbol }) { index ->
            AssetView(asset = assets[index], onItemClick)
        }
    }
}

@Composable
private fun AssetView(asset: AssetUiItem, onItemClick: (String) -> Unit) = Card(
    shape = RoundedCornerShape(10),
    modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(asset.name) },
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = asset.name)
        Row {
            Text(text = asset.symbol, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = asset.price)
        }
    }
}

@Preview
@Composable
private fun PreviewAssetView() {
    AssetsScreen(
        onNavigateToMarket = { assetId ->

        },
        viewModel = hiltViewModel()
    )
}