package me.puk3p.flyfix

import me.puk3p.flyfix.api.FlightHandler
import me.puk3p.flyfix.api.MessageProvider
import me.puk3p.flyfix.api.PermissionChecker
import me.puk3p.flyfix.command.FlyFixCommand
import me.puk3p.flyfix.handler.PlayerFlightHandler
import me.puk3p.flyfix.listener.TeleportListener
import me.puk3p.flyfix.manager.MessageManager
import me.puk3p.flyfix.permission.BukkitPermissionChecker
import org.bukkit.plugin.java.JavaPlugin

class Puk3pFlyFix : JavaPlugin() {
    override fun onEnable() {
        // Composition root — wire abstractions to implementations
        val messageProvider: MessageProvider = MessageManager(this)
        val vanishAvailable = server.pluginManager.getPlugin("PremiumVanish") != null
        val permissionChecker: PermissionChecker = BukkitPermissionChecker(vanishAvailable)
        val flightHandler: FlightHandler = PlayerFlightHandler(messageProvider)

        // Register Listeners
        server.pluginManager.registerEvents(
            TeleportListener(flightHandler, permissionChecker),
            this,
        )

        // Register Commands
        getCommand("flyfix")?.setExecutor(FlyFixCommand(messageProvider))

        logger.info("Puk3pFlyFix enabled successfully!")
    }
}
