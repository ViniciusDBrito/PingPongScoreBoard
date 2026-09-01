package br.edu.ifsp.scl.sc3047792.pingpongscoreboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class PingPongViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val scoreA: StateFlow<Int> = savedStateHandle.getStateFlow(KEY_SCORE_A, 0)
    val scoreB: StateFlow<Int> = savedStateHandle.getStateFlow(KEY_SCORE_B, 0)

    fun incrementA() {
        savedStateHandle[KEY_SCORE_A] = (savedStateHandle.get<Int>(KEY_SCORE_A) ?: 0) + 1
    }

    fun incrementB() {
        savedStateHandle[KEY_SCORE_B] = (savedStateHandle.get<Int>(KEY_SCORE_B) ?: 0) + 1
    }

    fun reset() {
        savedStateHandle[KEY_SCORE_A] = 0
        savedStateHandle[KEY_SCORE_B] = 0
    }

    private companion object {
        const val KEY_SCORE_A = "scoreA"
        const val KEY_SCORE_B = "scoreB"
    }
}
