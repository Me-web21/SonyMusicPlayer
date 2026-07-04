package com.meet.sonymusicplayer.ui
import com.meet.sonymusicplayer.ui.components.MiniPlayer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meet.sonymusicplayer.data.model.Song
import com.meet.sonymusicplayer.playback.MusicController
import com.meet.sonymusicplayer.playerstate.PlayerState
import com.meet.sonymusicplayer.ui.components.EmptyView
import com.meet.sonymusicplayer.ui.components.SearchBar
import com.meet.sonymusicplayer.ui.components.SongItem
import com.meet.sonymusicplayer.ui.components.TopBar
import androidx.compose.foundation.layout.Column

@Composable
fun HomeScreen(
    songs: List<Song>,
    onScanClick: () -> Unit,
    onSongClick: (Song) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredSongs = remember(songs, searchText) {

        if (searchText.isBlank()) {

            songs

        } else {

            songs.filter {

                it.title.contains(searchText, true) ||
                        it.artist.contains(searchText, true) ||
                        it.album.contains(searchText, true)

            }

        }

    }

    Scaffold(

        bottomBar = {

            MiniPlayer(

                currentSong = PlayerState.currentSong,

                isPlaying = PlayerState.isPlaying,

                onPlayPause = {

                    MusicController.togglePlayPause()

                },

                onNext = {

                    MusicController.next()

                },

                onPrevious = {

                    MusicController.previous()

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)

        ) {

            Spacer(modifier = Modifier.height(12.dp))

            TopBar()

            Spacer(modifier = Modifier.height(16.dp))

            SearchBar(

                searchText = searchText,

                onSearchChange = {

                    searchText = it

                }

            )

            Spacer(modifier = Modifier.height(12.dp))

            if (filteredSongs.isEmpty()) {

                EmptyView(
                    onScanClick = onScanClick
                )

            } else {

                LazyColumn(

                    modifier = Modifier.weight(1f),

                    contentPadding = PaddingValues(bottom = 90.dp)

                ) {

                    items(filteredSongs) { song ->

                        SongItem(

                            song = song,

                            onClick = {

                                onSongClick(song)

                            }

                        )

                    }

                }

            }

        }

    }

}