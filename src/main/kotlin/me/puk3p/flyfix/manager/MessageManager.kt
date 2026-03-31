package me.puk3p.flyfix.manager

import me.puk3p.flyfix.api.MessageProvider
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MessageManager(private val plugin: JavaPlugin) : MessageProvider {
    private var messagesConfig: FileConfiguration
    private val messagesFile: File

    init {
        messagesFile = File(plugin.dataFolder, "messages.yml")
        if (!messagesFile.exists()) {
            messagesFile.parentFile.mkdirs()
            plugin.saveResource("messages.yml", false)
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile)
    }

    override fun reload() {
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile)
    }

    override fun getMessage(key: String): String {
        val message = messagesConfig.getString(key)
        if (message.isNullOrEmpty()) return ""
        return ChatColor.translateAlternateColorCodes('&', message)
    }
}
