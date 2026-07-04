package com.meet.sonymusicplayer.playerstate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.meet.sonymusicplayer.data.model.Song

object PlayerState {

    var currentSong by mutableStateOf<Song?>(null)
        private set

    var isPlaying by mutableStateOf(false)
        private set

    fun setSong(song: Song) {
        currentSong = song
        isPlaying = true
    }

    fun pause() {
        isPlaying = false
    }

    fun resume() {
        isPlaying = true
    }

    fun stop() {
        currentSong = null
        isPlaying = false
    }
}