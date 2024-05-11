package com.enriselle.pokeapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.enriselle.pokeapp.ui.theme.PokeappTheme
import com.enriselle.pokeapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeCharacteristicsChip(
    characteristic: String,
    icon: ImageVector
) {
    PokeappTheme {
        FilterChip(
            selected = true,
            onClick = {  },
            label = {
                Text(
                    text = characteristic,
                    style = Typography.titleSmall
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = characteristic
                )
            }
        )
    }
}