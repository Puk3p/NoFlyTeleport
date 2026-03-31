package me.puk3p.flyfix.listener

import me.puk3p.flyfix.api.FlightHandler
import me.puk3p.flyfix.api.PermissionChecker
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerTeleportEvent

class TeleportListener(
    private val flightHandler: FlightHandler,
    private val permissionChecker: PermissionChecker,
) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerTeleport(event: PlayerTeleportEvent) {
        val player = event.player

        if (permissionChecker.canBypass(player)) {
            return
        }

        if (player.isFlying || player.allowFlight) {
            flightHandler.disableFlight(player)
        }
    }
}
