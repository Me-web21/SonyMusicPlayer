package com.meet.sonymusicplayer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.meet.sonymusicplayer.data.model.Song

@Composable
fun MiniPlayer(

    currentSong: Song?,

    isPlaying: Boolean,

    onPlayPause: () -> Unit,

    onNext: () -> Unit,

    onPrevious: () -> Unit

) {

    if (currentSong == null) return

    Card(

        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Text(

                    text = currentSong.title,

                    style = MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold,

                    maxLines = 1,

                    overflow = TextOverflow.Ellipsis

                )

                Text(

                    text = currentSong.artist,

                    style = MaterialTheme.typography.bodyMedium,

                    maxLines = 1,

                    overflow = TextOverflow.Ellipsis

                )

            }

            IconButton(
                onClick = onPrevious
            ) {

                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "Previous"
                )

            }

            IconButton(
                onClick = onPlayPause
            ) {

                Icon(

                    imageVector =
                        if (isPlaying)
                            Icons.Default.Pause
                        else
                            Icons.Default.PlayArrow,

                    contentDescription = "Play"

                )

            }

            IconButton(
                onClick = onNext
            ) {

                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Next"
                )

            }

        }

    }

}