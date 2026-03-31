package me.puk3p.flyfix.handler

import me.puk3p.flyfix.api.MessageProvider
import org.bukkit.entity.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlayerFlightHandlerTest {
    private lateinit var messageProvider: MessageProvider
    private lateinit var flightHandler: PlayerFlightHandler
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        messageProvider = mock()
        flightHandler = PlayerFlightHandler(messageProvider)
        player = mock()
    }

    @Test
    fun `should set isFlying and allowFlight to false`() {
        whenever(messageProvider.getMessage("fly-disabled")).thenReturn("Flight disabled!")

        flightHandler.disableFlight(player)

        verify(player).isFlying = false
        verify(player).allowFlight = false
    }

    @Test
    fun `should send message when message is not empty`() {
        whenever(messageProvider.getMessage("fly-disabled")).thenReturn("Flight disabled!")

        flightHandler.disableFlight(player)

        verify(player).sendMessage("Flight disabled!")
    }

    @Test
    fun `should not send message when message is empty`() {
        whenever(messageProvider.getMessage("fly-disabled")).thenReturn("")

        flightHandler.disableFlight(player)

        verify(player, never()).sendMessage(any<String>())
    }
}
