package me.puk3p.flyfix.api

interface MessageProvider {
    fun getMessage(key: String): String

    fun reload()
}
