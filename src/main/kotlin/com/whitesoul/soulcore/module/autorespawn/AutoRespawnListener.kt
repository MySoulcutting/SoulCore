package com.whitesoul.soulcore.module.autorespawn

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit

object AutoRespawnListener: Listener {
    @EventHandler
    fun onRespawn(e: PlayerDeathEvent) {
        val player = e.entity
        submit(delay = 5) {
            player.spigot().respawn()
        }
    }
}