package com.enriselle.pokeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.enriselle.pokeapp.ui.theme.PokeappTheme
import com.enriselle.pokeapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeCard(
    id: String,
    name: String,
    sprite: String,
    types: List<String>,
    onCardClicked: () -> Unit
) {
    val typeColors = mapOf(
        "grass" to Color(android.graphics.Color.parseColor("#9bcc50")),
        "fire" to Color(android.graphics.Color.parseColor("#fd7d24")),
        "water" to Color(android.graphics.Color.parseColor("#4592c4")),
        "poison" to Color(android.graphics.Color.parseColor("#b97fc9")),
        "flying" to Color(android.graphics.Color.parseColor("#3dc7ef")),
        "bug" to Color(android.graphics.Color.parseColor("#729f3f")),
        "normal" to Color(android.graphics.Color.parseColor("#a4acaf"))
    )

    PokeappTheme {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            onClick = {
                onCardClicked()
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(10f)
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize(),
                            model = sprite,
                            contentDescription = "$name sprite"
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(6f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "N.° $id",
                        color = Color(android.graphics.Color.parseColor("#919ebd"))
                    )
                    Text(
                        text = name,
                        style = Typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        types.forEach { type ->
                            PokeTypeChip(
                                type = type,
                                typeColor = typeColors[type]
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PokeCardPreview() {
    PokeCard(
        name = "Pikachu",
        sprite = "",
        types = listOf(
            "fire",
            "poison"
        ),
        onCardClicked = {

        },
        id = "1"
    )
}