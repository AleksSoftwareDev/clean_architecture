package com.ethermail.androidchallenge.domain.usecase.assets

import com.ethermail.androidchallenge.domain.mappers.CryptoMapper.toData
import com.ethermail.androidchallenge.domain.BaseUseCase
import com.ethermail.androidchallenge.domain.usecase.market.CryptoRepository
import com.ethermail.androidchallenge.presentation.assets.AssetUiItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAssetsUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : BaseUseCase<Unit, Flow<List<AssetUiItem>?>>() {
    override fun execute(params: Unit): Flow<List<AssetUiItem>?> {
        return cryptoRepository.getAssetsApiCall().map { it?.toData() }
    }
}