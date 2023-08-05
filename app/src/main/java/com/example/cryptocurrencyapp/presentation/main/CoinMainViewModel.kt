package com.example.cryptocurrencyapp.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.usecases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinMainViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().collect { result ->
                _state.value = when (result) {
                    is Resource.Success -> CoinListState(coins = result.data ?: emptyList())
                    is Resource.Error -> CoinListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    is Resource.Loading -> CoinListState(isLoading = true)
                }
            }
        }
    }
}