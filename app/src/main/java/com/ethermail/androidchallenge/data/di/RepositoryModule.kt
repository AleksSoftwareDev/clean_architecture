package com.ethermail.androidchallenge.data.di

import com.ethermail.androidchallenge.data.datasource.remote.CoincapService
import com.ethermail.androidchallenge.data.repository.market.CryptoRepositoryImpl
import com.ethermail.androidchallenge.domain.usecase.market.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMarketRepository(apiService: CoincapService): CryptoRepository {
        return CryptoRepositoryImpl(apiService)
    }
}