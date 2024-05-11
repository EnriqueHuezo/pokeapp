package com.enriselle.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.enriselle.pokeapp.data.source.network.remote.PokemonInfo
import com.enriselle.pokeapp.features.DetailsScreen
import com.enriselle.pokeapp.features.MainScreen
import com.enriselle.pokeapp.ui.theme.PokeappTheme

sealed class GRAPH(val route: String) {
    data object MAIN: GRAPH(route = "MAIN")
}

sealed class Main(val route: String) {
    data object MainScreen: Main(route = "Main")
    data object DetailsScreen: Main(route = "Details")
}

@Composable
fun RootNavigation(
    navController: NavHostController = rememberNavController()
) {
    PokeappTheme {
        NavHost(
            navController = navController,
            route = GRAPH.MAIN.route,
            startDestination = Main.MainScreen.route
        ) {
            composable(Main.MainScreen.route) {
                MainScreen(
                    onCardClicked = { pokemonSelected ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("pokemon", pokemonSelected)
                        navController.navigate(Main.DetailsScreen.route)
                    }
                )
            }

            composable(Main.DetailsScreen.route) {
                val sharedPokemon = navController.previousBackStackEntry?.savedStateHandle?.get<PokemonInfo>("pokemon")
                DetailsScreen(
                    pokemon = sharedPokemon
                )
            }
        }
    }
}