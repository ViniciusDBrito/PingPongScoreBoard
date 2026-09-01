package br.edu.ifsp.scl.sc3047792.pingpongscoreboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PingPongUiState(
    val scoreA: Int = 0,
    val scoreB: Int = 0
)

class PingPongViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PingPongUiState())
    val uiState: StateFlow<PingPongUiState> = _uiState.asStateFlow()

    fun incrementA() {
        _uiState.update { it.copy(scoreA = it.scoreA + 1) }
    }

    fun incrementB() {
        _uiState.update { it.copy(scoreB = it.scoreB + 1) }
    }

    fun reset() {
        _uiState.update { PingPongUiState() }
    }
}