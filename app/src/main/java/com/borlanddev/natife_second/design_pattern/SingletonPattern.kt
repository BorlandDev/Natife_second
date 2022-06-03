package com.borlanddev.natife_second.design_pattern
/*

fun main() {
    val player1 = Player("player1")
    val player2 = Player("player2")
    val player3 = Player("player3")

    val gameConfig = GameConfig.getInstance()

    gameConfig.addPlayer(player1, player2, player3)
    gameConfig.showAmountPlayers()
}

class Player(val name: String)

class GameConfig private constructor() {
    private var players: List<Player> = listOf()

    init { println("Amount players: ${players.size}") }

    fun showAmountPlayers() = println("Amount players: ${players.size}")

    fun addPlayer(vararg player: Player) { players = players + player }

    companion object {
        @Volatile
        private var instance: GameConfig? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: GameConfig().also { instance = it }
            }
        }

}


 */