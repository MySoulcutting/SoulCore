package com.whitesoul.soulcore.module.nohunger

import org.bukkit.entity.Player
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.SubscribeEvent

object NoHungerListener {
    @SubscribeEvent
    fun onPlayerHungerChange(e: FoodLevelChangeEvent) {
        val player = e.entity as Player
        if (player.level >= 30) {
            e.isCancelled = true
        }
    }
    @SubscribeEvent
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        if (player.level >= 30) {
            e.player.foodLevel = 20
        }
    }
}