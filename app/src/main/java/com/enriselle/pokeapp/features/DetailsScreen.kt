package com.enriselle.pokeapp.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.enriselle.pokeapp.data.source.network.remote.Ability
import com.enriselle.pokeapp.data.source.network.remote.DataAbilities
import com.enriselle.pokeapp.data.source.network.remote.DataStats
import com.enriselle.pokeapp.data.source.network.remote.DataType
import com.enriselle.pokeapp.data.source.network.remote.PokemonInfo
import com.enriselle.pokeapp.data.source.network.remote.Sprites
import com.enriselle.pokeapp.data.source.network.remote.Stat
import com.enriselle.pokeapp.data.source.network.remote.Type
import com.enriselle.pokeapp.ui.components.PokeCharacteristicsChip
import com.enriselle.pokeapp.ui.components.PokeStatBar
import com.enriselle.pokeapp.ui.components.PokeTypeChip
import com.enriselle.pokeapp.ui.theme.PokeappTheme
import com.enriselle.pokeapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    pokemon: PokemonInfo?
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

    val setColor = typeColors[pokemon?.types?.get(0)?.type?.name]
    val setColorAlpha = setColor?.copy(alpha = 0.5f)

    PokeappTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = setColor ?: Color.Transparent
                    ),
                    title = {
                        Text(
                            text = "Pokemon information",
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(setColor ?: Color.Transparent)
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                if (pokemon != null) {
                    Box(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = pokemon.name,
                                style = Typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Text(
                                text = "N.° ${pokemon.id}",
                                color = Color.White,
                                style = Typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Use Box for overlapping elements
                    Box(
                        modifier = Modifier
                            .weight(10f)
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .offset(y = (-125).dp)
                                .zIndex(10f)
                                .size(200.dp),
                            model = pokemon.sprites.front_default,
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "${pokemon.name} sprite"
                        )


                        ElevatedCard(
                            modifier = Modifier
                                .matchParentSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        top = 75.dp,
                                        bottom = 16.dp,
                                        start = 16.dp,
                                        end = 16.dp
                                    ),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    pokemon.types.forEach { type ->
                                        PokeTypeChip(
                                            type = type.type.name,
                                            typeColor = typeColors[type.type.name])
                                    }
                                }

                                Column {
                                    Text(
                                        text = "Characteristics",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement
                                            .spacedBy(16.dp)
                                    ) {
                                        PokeCharacteristicsChip(
                                            characteristic = "${pokemon.weight} KG",
                                            icon = Icons.Default.FitnessCenter
                                        )

                                        PokeCharacteristicsChip(
                                            characteristic = "${pokemon.height*10} CM",
                                            icon = Icons.Default.SquareFoot
                                        )
                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Base stats",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    pokemon.stats.forEach { stat ->
                                        PokeStatBar(
                                            fieldName = stat.stat.name,
                                            baseStat = stat.base_stat,
                                            color = setColor,
                                            colorTracker = setColorAlpha
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun DetailsScreenPreview() {
    PokeappTheme {
        DetailsScreen(
            pokemon = PokemonInfo(
                id = 1,
                name = "Pikachu",
                sprites = Sprites("", ""),
                types = listOf(
                    DataType((Type("trueno")))
                ),
                stats = listOf(
                    DataStats(10, Stat("hp"))
                ),
                height = 100,
                weight = 100,
                abilities = listOf(
                    DataAbilities(Ability("impaktrueno"))
                )
            )
        )
    }
}
