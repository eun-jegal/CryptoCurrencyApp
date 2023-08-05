package com.example.cryptocurrencyapp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Constants.PARAM_ID
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.usecases.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_ID)?.let { id ->
            getCoinDetail(id)
        }
    }

    private fun getCoinDetail(id: String) {
        viewModelScope.launch {
            getCoinDetailUseCase(id).collect { result ->
                _state.value = when (result) {
                    is Resource.Success -> CoinDetailState(coin = result.data)
                    is Resource.Error -> CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    is Resource.Loading -> CoinDetailState(isLoading = true)
                }
            }
        }
    }
}