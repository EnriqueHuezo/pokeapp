package com.enriselle.pokeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enriselle.pokeapp.ui.theme.PokeappTheme

@Composable
fun PokeStatBar(
    fieldName: String,
    baseStat: Int,
    maxStat: Int = 200,
    color: Color?,
    colorTracker: Color?
) {
    val fieldNameMap = mapOf(
        "hp" to "HP",
        "attack" to "ATK",
        "defense" to "DEF",
        "special-attack" to "SATK",
        "special-defense" to "SDEF",
        "speed" to "SPD"
    )

    PokeappTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (color != null) {
                Text(
                    modifier = Modifier
                        .width(40.dp),
                    text = fieldNameMap[fieldName] ?: "??",
                    color = color,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
            )
            Text(
                modifier = Modifier
                    .width(30.dp),
                text = baseStat.toString()
            )

            if (color != null) {
                if (colorTracker != null) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(),
                        progress = baseStat.toFloat() / maxStat.toFloat(),
                        color = color,
                        trackColor = colorTracker
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PokeStatBarPreview() {
    PokeappTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            PokeStatBar(
                "hp",
                100,
                200,
                color = Color(android.graphics.Color.parseColor("#9bcc50")),
                colorTracker = Color(android.graphics.Color.parseColor("#9bcc50")).copy(alpha = 0.5f)
            )
        }
    }
}
