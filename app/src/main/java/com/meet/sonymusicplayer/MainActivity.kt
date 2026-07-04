package com.meet.sonymusicplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import com.meet.sonymusicplayer.data.model.Song
import com.meet.sonymusicplayer.data.scanner.MusicScanner
import com.meet.sonymusicplayer.playback.MusicController
import com.meet.sonymusicplayer.playback.MusicPlayer
import com.meet.sonymusicplayer.ui.HomeScreen
import com.meet.sonymusicplayer.ui.theme.SonyMusicPlayerTheme

class MainActivity : ComponentActivity() {

    private lateinit var musicScanner: MusicScanner
    private lateinit var musicPlayer: MusicPlayer

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            // optional: auto scan after permission
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        musicScanner = MusicScanner(this)
        musicPlayer = MusicPlayer(this)

        MusicController.initialize(musicPlayer)

        requestMusicPermission()

        setContent {

            SonyMusicPlayerTheme {

                var songs by remember {
                    mutableStateOf<List<Song>>(emptyList())
                }

                HomeScreen(
                    songs = songs,

                    onScanClick = {
                        songs = musicScanner.getAllSongs()
                        MusicController.setPlaylist(songs)
                    },

                    onSongClick = { song ->
                        MusicController.play(song)
                    }
                )
            }
        }
    }

    private fun requestMusicPermission() {

        val permission =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_AUDIO
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }

        val granted = ContextCompat.checkSelfPermission(this, permission) ==
                PackageManager.PERMISSION_GRANTED

        if (!granted) {
            permissionLauncher.launch(permission)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.release()
    }
}