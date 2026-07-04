package com.meet.sonymusicplayer.playback

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.meet.sonymusicplayer.data.model.Song
import com.meet.sonymusicplayer.playerstate.PlayerState

class MusicPlayer(context: Context) {

    private val player = ExoPlayer.Builder(context).build()

    private var playlist: List<Song> = emptyList()
    private var currentIndex = -1

    init {
        player.addListener(object : Player.Listener {

            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    nextSong()
                }
            }

            override fun onMediaItemTransition(
                mediaItem: MediaItem?,
                reason: Int
            ) {
                currentIndex = player.currentMediaItemIndex

                if (currentIndex in playlist.indices) {
                    PlayerState.setSong(playlist[currentIndex])
                }
            }
        })
    }

    /**
     * Set complete playlist into ExoPlayer only once.
     */
    fun setPlaylist(songs: List<Song>) {

        playlist = songs

        val mediaItems = songs.map {
            MediaItem.fromUri(it.uri)
        }

        player.setMediaItems(mediaItems)
        player.prepare()
    }

    fun getCurrentSong(): Song? {

        return if (currentIndex in playlist.indices)
            playlist[currentIndex]
        else
            null
    }

    /**
     * Play song by index.
     */
    fun playSongAt(index: Int) {

        if (playlist.isEmpty()) return
        if (index !in playlist.indices) return

        currentIndex = index

        player.seekTo(index, 0)
        player.play()

        PlayerState.setSong(playlist[index])
        PlayerState.resume()
    }

    /**
     * Play using Song object.
     */
    fun playSong(song: Song) {

        val index = playlist.indexOfFirst {
            it.uri == song.uri
        }

        if (index != -1) {
            playSongAt(index)
        }
    }

    fun nextSong() {

        if (playlist.isEmpty()) return

        val nextIndex =
            if (currentIndex + 1 >= playlist.size)
                0
            else
                currentIndex + 1

        playSongAt(nextIndex)
    }

    fun previousSong() {

        if (playlist.isEmpty()) return

        val previousIndex =
            if (currentIndex - 1 < 0)
                playlist.lastIndex
            else
                currentIndex - 1

        playSongAt(previousIndex)
    }

    fun pause() {
        player.pause()
        PlayerState.pause()
    }

    fun resume() {
        player.play()
        PlayerState.resume()
    }

    fun togglePlayPause() {

        if (player.isPlaying)
            pause()
        else
            resume()
    }

    fun stop() {

        player.stop()
        PlayerState.stop()
    }

    fun isPlaying(): Boolean {
        return player.isPlaying
    }

    fun currentSong(): Song? {
        return getCurrentSong()
    }

    fun release() {
        player.release()
    }
}