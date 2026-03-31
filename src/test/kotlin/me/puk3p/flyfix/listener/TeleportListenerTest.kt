package me.puk3p.flyfix.listener

import me.puk3p.flyfix.api.FlightHandler
import me.puk3p.flyfix.api.PermissionChecker
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TeleportListenerTest {
    private lateinit var flightHandler: FlightHandler
    private lateinit var permissionChecker: PermissionChecker
    private lateinit var listener: TeleportListener
    private lateinit var player: Player
    private lateinit var event: PlayerTeleportEvent

    @BeforeEach
    fun setUp() {
        flightHandler = mock()
        permissionChecker = mock()
        listener = TeleportListener(flightHandler, permissionChecker)

        player = mock()
        val world: World = mock()
        val from = Location(world, 0.0, 64.0, 0.0)
        val to = Location(world, 100.0, 64.0, 100.0)
        event = PlayerTeleportEvent(player, from, to)
    }

    @Test
    fun `should delegate to flight handler when player is flying and has no bypass`() {
        whenever(permissionChecker.canBypass(player)).thenReturn(false)
        whenever(player.isFlying).thenReturn(true)
        whenever(player.allowFlight).thenReturn(true)

        listener.onPlayerTeleport(event)

        verify(flightHandler).disableFlight(player)
    }

    @Test
    fun `should delegate to flight handler when only allowFlight is true`() {
        whenever(permissionChecker.canBypass(player)).thenReturn(false)
        whenever(player.isFlying).thenReturn(false)
        whenever(player.allowFlight).thenReturn(true)

        listener.onPlayerTeleport(event)

        verify(flightHandler).disableFlight(player)
    }

    @Test
    fun `should not disable flight when player can bypass`() {
        whenever(permissionChecker.canBypass(player)).thenReturn(true)

        listener.onPlayerTeleport(event)

        verify(flightHandler, never()).disableFlight(any())
    }

    @Test
    fun `should not disable flight when player is not flying`() {
        whenever(permissionChecker.canBypass(player)).thenReturn(false)
        whenever(player.isFlying).thenReturn(false)
        whenever(player.allowFlight).thenReturn(false)

        listener.onPlayerTeleport(event)

        verify(flightHandler, never()).disableFlight(any())
    }
}
