package br.edu.ifsp.scl.sc3047792.pingpongscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3047792.pingpongscoreboard.ui.theme.PingPongScoreBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PingPongScoreBoardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PingPongScreenRemember()
                }
            }
        }
    }
}

@Composable
fun PingPongScreenRemember() {
    var scoreA by remember { mutableStateOf(0) }
    var scoreB by remember { mutableStateOf(0) }

    PingPongLayout(
        scoreA = scoreA,
        scoreB = scoreB,
        onIncrementA = { scoreA++ },
        onIncrementB = { scoreB++ },
        onReset = {
            scoreA = 0
            scoreB = 0
        }
    )
}

@Composable
fun PingPongLayout(
    scoreA: Int,
    scoreB: Int,
    onIncrementA: () -> Unit,
    onIncrementB: () -> Unit,
    onReset: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            TeamColumn(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                teamName = "Time A",
                score = scoreA,
                onIncrement = onIncrementA
            )
            TeamColumn(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                teamName = "Time B",
                score = scoreB,
                onIncrement = onIncrementB
            )
        }
        Button(
            onClick = onReset,
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        ) {
            Text("Reiniciar partida")
        }
    }
}

@Composable
private fun TeamColumn(
    modifier: Modifier = Modifier,
    teamName: String,
    score: Int,
    onIncrement: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = teamName, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$score", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onIncrement) {
            Text("+1")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PingPongScreenRememberPreview() {
    var scoreA by remember { mutableStateOf(0) }
    var scoreB by remember { mutableStateOf(0) }

    PingPongScoreBoardTheme {
        PingPongLayout(
            scoreA = scoreA,
            scoreB = scoreB,
            onIncrementA = { scoreA++ },
            onIncrementB = { scoreB++ },
            onReset = {
                scoreA = 0
                scoreB = 0
            }
        )
    }
}