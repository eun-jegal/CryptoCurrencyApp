package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.dataobject.CoinDetailDto
import com.example.cryptocurrencyapp.data.dataobject.CoinDto
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val apiService: CoinPaprikaApi
): CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return apiService.getCoins()
    }

    override suspend fun getCoinDetail(id: String): CoinDetailDto {
        return apiService.getCoinById(id)
    }
}