package com.meet.sonymusicplayer.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(

    searchText: String,

    onSearchChange: (String) -> Unit

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {

        OutlinedTextField(

            value = searchText,

            onValueChange = onSearchChange,

            modifier = Modifier
                .fillMaxWidth(),

            placeholder = {
                Text("Search Songs...")
            },

            leadingIcon = {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )

            },

            singleLine = true,

            colors = OutlinedTextFieldDefaults.colors()

        )

    }

}