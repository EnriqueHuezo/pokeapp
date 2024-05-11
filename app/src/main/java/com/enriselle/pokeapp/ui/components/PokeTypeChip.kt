package com.enriselle.pokeapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.enriselle.pokeapp.ui.theme.PokeappTheme
import com.enriselle.pokeapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeTypeChip(
    type: String,
    typeColor: Color?
) {
    PokeappTheme {
        FilterChip(
            selected = true,
            onClick = {  },
            label = {
                Text(
                    text = type,
                    style = Typography.titleSmall
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = typeColor ?: Color.Transparent,
                selectedLabelColor = Color.White
            )
        )
    }
}