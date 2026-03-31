package me.puk3p.flyfix.command

import me.puk3p.flyfix.api.MessageProvider
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlyFixCommand(
    private val messageProvider: MessageProvider,
) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>,
    ): Boolean {
        if (sender is Player && !sender.hasPermission("puk3pflyfix.admin")) {
            sender.sendMessage(messageProvider.getMessage("no-permission"))
            return true
        }

        if (args.isNotEmpty() && args[0].equals("reload", ignoreCase = true)) {
            messageProvider.reload()
            sender.sendMessage(messageProvider.getMessage("reload-success"))
            return true
        }

        sender.sendMessage(messageProvider.getMessage("invalid-command"))
        return true
    }
}
