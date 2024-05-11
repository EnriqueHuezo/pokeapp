package com.enriselle.pokeapp.features

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enriselle.pokeapp.data.source.network.remote.PokemonInfo
import com.enriselle.pokeapp.ui.components.PokeCard
import com.enriselle.pokeapp.ui.theme.PokeappTheme

@Composable
fun MainScreen(
    onCardClicked: (pokemon: PokemonInfo) -> Unit
) {
    val viewmodel = hiltViewModel<PokeViewModel>()
    val listPokemon = viewmodel.listPokemon.collectAsState()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = context) {
        viewmodel.validationEvents.collect { event ->
            when(event) {
                is PokeViewModel.ValidationEvent.Error -> {

                }
                is PokeViewModel.ValidationEvent.Loading -> {
                    isLoading = event.isLoading
                }
                PokeViewModel.ValidationEvent.Success -> {

                }
            }
        }
    }

    PokeappTheme {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listPokemon.value) {
                    val getTypes = it.types.map { it.type.name }
                    PokeCard(
                        id = it.id.toString(),
                        name = it.name,
                        sprite = it.sprites.front_default,
                        types = getTypes,
                        onCardClicked = {
                            onCardClicked(it)
                        }
                    )
                }
            }
        }
    }
}



