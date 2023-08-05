package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.dataobject.CoinDetailDto
import com.example.cryptocurrencyapp.data.dataobject.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetail(id: String): CoinDetailDto
}