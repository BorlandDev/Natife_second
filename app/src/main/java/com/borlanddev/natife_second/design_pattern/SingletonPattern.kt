package com.borlanddev.natife_second.design_pattern

fun main() {
    val player1 = Player("player1")
    val player2 = Player("player2")
    val player3 = Player("player3")
    val player4 = Player("player4")

    GameConfig.addPlayer(player1, player2, player3)
    GameConfig.showAmountPlayers()

    GameConfig.addPlayer(player4)
    UseSingleton().show()
}


class Player(val name: String)

class UseSingleton {
    fun show() {
        GameConfig.showAmountPlayers()
    }
}

object GameConfig {
    private var players: List<Player> = listOf()

    init { println("Amount players: ${players.size}") }

    fun showAmountPlayers() = println("Amount players: ${players.size}")

    fun addPlayer(vararg player: Player) {
        players = players + player
    }
}


