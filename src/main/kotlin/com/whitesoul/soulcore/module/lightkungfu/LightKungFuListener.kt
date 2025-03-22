package com.whitesoul.soulcore.module.lightkungfu

import cc.bukkitPlugin.pds.events.PlayerDataLoadCompleteEvent
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerToggleFlightEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit
import taboolib.common.util.Location
import taboolib.module.effect.createCircle
import java.util.*

object LightKungFuListener: Listener {
    private val jumps = HashMap<UUID, Int>()
    private val jump: Int = 3
    private val multiply = 1.5
    private val y = 1

    @SubscribeEvent
    fun onPlayerJoin(e: PlayerDataLoadCompleteEvent) {
        val player = e.player
        jumps[player.uniqueId] = 0
        if (player.hasPermission("soulcore.lightkungfu")) {
            player.allowFlight = true

        }
    }
    @SubscribeEvent
    fun onEntityFallDamage(e: EntityDamageEvent) {
        if (e.entity.type == EntityType.PLAYER && e.cause == EntityDamageEvent.DamageCause.FALL) {
            e.isCancelled = true
        }
    }
    @SubscribeEvent
    fun onLightKungFuEvent(e: PlayerToggleFlightEvent) {
        val player = e.player
        if (player.hasPermission("soulcore.lightkungfu")) {
            // 取消飞行
            e.isCancelled = true
            // 设置跳跃
            val vector = player.location.direction
            vector.multiply(multiply)
            vector.setY(y)
            player.velocity = vector
            // 播放音效
            player.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 1f, 0.9f)
            // 播放特效
            val loc = Location(player.world.name,player.location.x,player.location.y,player.location.z)
            val circle = createCircle(loc, 0.3) { circleLoc: Location ->
                val bkLoc = Location(Bukkit.getWorld(circleLoc.world!!), circleLoc.x, circleLoc.y, circleLoc.z)
                player.world.spawnParticle(Particle.SPELL_INSTANT,bkLoc,1)
            }
            circle.alwaysShowAsync()
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