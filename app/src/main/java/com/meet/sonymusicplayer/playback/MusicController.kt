package com.meet.sonymusicplayer.playback

import com.meet.sonymusicplayer.data.model.Song

object MusicController {

    private var musicPlayer: MusicPlayer? = null

    fun initialize(player: MusicPlayer) {
        musicPlayer = player
    }

    fun setPlaylist(songs: List<Song>) {
        musicPlayer?.setPlaylist(songs)
    }

    fun play(song: Song) {
        musicPlayer?.playSong(song)
    }

    fun pause() {
        musicPlayer?.pause()
    }

    fun resume() {
        musicPlayer?.resume()
    }

    fun togglePlayPause() {
        musicPlayer?.togglePlayPause()
    }

    fun next() {
        musicPlayer?.nextSong()
    }

    fun previous() {
        musicPlayer?.previousSong()
    }

    fun stop() {
        musicPlayer?.stop()
    }

    fun isPlaying(): Boolean {
        return musicPlayer?.isPlaying() ?: false
    }

    fun currentSong(): Song? {
        return musicPlayer?.currentSong()
    }
}