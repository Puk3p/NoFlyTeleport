package me.puk3p.flyfix.permission

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BukkitPermissionCheckerTest {
    private lateinit var checker: BukkitPermissionChecker
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        checker = BukkitPermissionChecker()
        player = mock()
    }

    @Test
    fun `creative players can bypass`() {
        whenever(player.gameMode).thenReturn(GameMode.CREATIVE)
        assertTrue(checker.canBypass(player))
    }

    @Test
    fun `spectator players can bypass`() {
        whenever(player.gameMode).thenReturn(GameMode.SPECTATOR)
        assertTrue(checker.canBypass(player))
    }

    @Test
    fun `survival player with bypass permission can bypass`() {
        whenever(player.gameMode).thenReturn(GameMode.SURVIVAL)
        whenever(player.hasPermission("puk3pflyfix.bypass")).thenReturn(true)
        assertTrue(checker.canBypass(player))
    }

    @Test
    fun `survival player without bypass permission cannot bypass`() {
        whenever(player.gameMode).thenReturn(GameMode.SURVIVAL)
        whenever(player.hasPermission("puk3pflyfix.bypass")).thenReturn(false)
        assertFalse(checker.canBypass(player))
    }

    @Test
    fun `player with admin permission is admin`() {
        whenever(player.hasPermission("puk3pflyfix.admin")).thenReturn(true)
        assertTrue(checker.isAdmin(player))
    }

    @Test
    fun `player without admin permission is not admin`() {
        whenever(player.hasPermission("puk3pflyfix.admin")).thenReturn(false)
        assertFalse(checker.isAdmin(player))
    }
}
