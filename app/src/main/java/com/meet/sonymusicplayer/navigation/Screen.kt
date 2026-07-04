package com.meet.sonymusicplayer.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Player : Screen("player")

    object Search : Screen("search")

    object Playlist : Screen("playlist")

    object Settings : Screen("settings")
}