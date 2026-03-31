package me.puk3p.flyfix.api

import org.bukkit.entity.Player

interface PermissionChecker {
    fun canBypass(player: Player): Boolean

    fun isAdmin(player: Player): Boolean
}
