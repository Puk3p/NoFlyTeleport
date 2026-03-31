package me.puk3p.flyfix.permission

import me.puk3p.flyfix.api.PermissionChecker
import org.bukkit.GameMode
import org.bukkit.entity.Player

class BukkitPermissionChecker : PermissionChecker {
    override fun canBypass(player: Player): Boolean {
        if (player.gameMode == GameMode.CREATIVE || player.gameMode == GameMode.SPECTATOR) {
            return true
        }
        return player.hasPermission("puk3pflyfix.bypass")
    }

    override fun isAdmin(player: Player): Boolean {
        return player.hasPermission("puk3pflyfix.admin")
    }
}
