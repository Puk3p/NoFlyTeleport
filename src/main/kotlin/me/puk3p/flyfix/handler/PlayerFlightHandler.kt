package me.puk3p.flyfix.handler

import me.puk3p.flyfix.api.FlightHandler
import me.puk3p.flyfix.api.MessageProvider
import org.bukkit.entity.Player

class PlayerFlightHandler(private val messageProvider: MessageProvider) : FlightHandler {
    override fun disableFlight(player: Player) {
        player.isFlying = false
        player.allowFlight = false

        val message = messageProvider.getMessage("fly-disabled")
        if (message.isNotEmpty()) {
            player.sendMessage(message)
        }
    }
}
