package com.whitesoul.soulcore.module.lightkungfu

import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerToggleFlightEvent
import taboolib.common.platform.function.submit
import taboolib.common.util.Location
import taboolib.module.effect.createCircle
import java.util.UUID

object LightKungFuListener: Listener {
    private val jumps = HashMap<UUID, Int>()
    private val jump: Int = 3
    private val multiply = 1.5
    private val y = 1

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        if (player.hasPermission("soulcore.lightkungfu")) {
            submit(delay = 60) {
                jumps[player.uniqueId] = 0
                player.allowFlight = true
            }
        }
    }
    @EventHandler
    fun onEntityFallDamage(e: EntityDamageEvent) {
        if (e.entity.type == EntityType.PLAYER && e.cause == EntityDamageEvent.DamageCause.FALL) {
            e.isCancelled = true
        }
    }
    @EventHandler
    fun onLightKungFuEvent(e: PlayerToggleFlightEvent) {
        val player = e.player
        if (player.gameMode == GameMode.CREATIVE && player.isFlying) return
        if (player.hasPermission("soulcore.lightkungfu")) {
            // 取消飞行
            e.isCancelled = true
            // 设置跳跃
            val vector = player.location.direction
            vector.multiply(multiply)
            vector.setY(y)
            player.velocity = vector
            // 播放音效
            player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
            // 播放特效
            val loc = Location(player.world.name,player.location.x,player.location.y,player.location.z)
            val circle = createCircle(loc, 0.3) { circleLoc: Location ->
                val bkLoc = Location(Bukkit.getWorld(circleLoc.world!!), circleLoc.x, circleLoc.y, circleLoc.z)
                player.world.spawnParticle(Particle.SNOWBALL,bkLoc,1)
            }
            circle.show()
        }
        val oldJumps = jumps[player.uniqueId]
        jumps[player.uniqueId] = oldJumps!! + 1
        if (jumps[player.uniqueId] == jump) {
            player.allowFlight = false
            submit(delay = 60) {
                player.allowFlight = true
                jumps[player.uniqueId] = 0
            }
        }
    }
}