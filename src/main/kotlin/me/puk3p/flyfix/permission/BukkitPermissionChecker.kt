package me.puk3p.flyfix.permission

import de.myzelyam.api.vanish.VanishAPI
import me.puk3p.flyfix.api.PermissionChecker
import org.bukkit.GameMode
import org.bukkit.entity.Player

class BukkitPermissionChecker(
    private val vanishAvailable: Boolean,
) : PermissionChecker {
    override fun canBypass(player: Player): Boolean {
        if (player.gameMode == GameMode.CREATIVE || player.gameMode == GameMode.SPECTATOR) {
            return true
        }
        if (vanishAvailable && isVanished(player)) {
            return true
        }
        return player.hasPermission("puk3pflyfix.bypass")
    }

    override fun isAdmin(player: Player): Boolean {
        return player.hasPermission("puk3pflyfix.admin")
    }

    private fun isVanished(player: Player): Boolean {
        return try {
            VanishAPI.isInvisible(player)
        } catch (_: Exception) {
            false
        }
    }
}
