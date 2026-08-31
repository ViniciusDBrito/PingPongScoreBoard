package br.edu.ifsp.scl.sc3047792.pingpongscoreboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PingPongViewModel : ViewModel() {

    var scoreA by mutableStateOf(0)
        private set

    var scoreB by mutableStateOf(0)
        private set

    fun incrementA() {
        scoreA++
    }

    fun incrementB() {
        scoreB++
    }

    fun reset() {
        scoreA = 0
        scoreB = 0
    }
}