package com.whitesoul.soulcore.module.dropitemeffect

import com.whitesoul.soulcore.file.FileDropItemEffect
import eos.moe.dragoncore.network.PacketSender
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ItemDespawnEvent
import org.bukkit.event.entity.ItemMergeEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import taboolib.common.platform.function.submit
import taboolib.module.chat.uncolored
import java.util.*
import kotlin.collections.HashMap

object DropItemEffectListener: Listener {
    private val dropItemMap = HashMap<UUID, Entity>()

    @EventHandler
    fun onDropItemEvent(e: ItemSpawnEvent) {
        if (FileDropItemEffect.dropItemEffect.containsKey(e.entity?.itemStack?.itemMeta?.displayName?.uncolored())) {
            val dropItem = FileDropItemEffect.dropItemEffect[e.entity.itemStack.itemMeta.displayName.uncolored()]
            val effectPath = dropItem?.effectPath
            val time = dropItem?.time!!
            val world = e.entity.world
            val itemLoc = Location(e.entity.world, e.entity.location.x, e.entity.location.y - 1, e.entity.location.z)
            // 生成盔甲架
            val effectEntity: ArmorStand = world.spawnEntity(itemLoc, EntityType.ARMOR_STAND) as ArmorStand
            e.entity.addPassenger(effectEntity)
            effectEntity.customName = "drop-item-effect"
            effectEntity.isInvulnerable = true
            effectEntity.isVisible = false
            effectEntity.isSmall = true
            val effectUUID = effectEntity.uniqueId
            dropItemMap[e.entity.uniqueId] = e.entity
            // 当掉落在地上时触发
            submit(period = 1) {
                if (e.entity.isOnGround) {
                    // 发包特效
                    for (player in e.entity.world.players) {
                        PacketSender.addParticle(
                            player,
                            effectPath,
                            e.entity.uniqueId.toString(),
                            effectUUID.toString(),
                            "0,0,0",
                            time
                        )
                    }
                    cancel()
                }
            }
        }
    }
    // 物品合并取消
    @EventHandler
    fun onItemMergeEvent(e: ItemMergeEvent) {
        val itemEntity = dropItemMap[e.entity.uniqueId]
        if (itemEntity != null) {
            e.isCancelled = true
        }
    }
    // 物品消失 清除特效
    @EventHandler
    fun onDropItemRemoveEvent(e: ItemDespawnEvent) {
        val itemEntity = dropItemMap[e.entity.uniqueId]
        if (itemEntity != null) {
            for (player in e.entity.world.players) {
                PacketSender.removeParticle(player, itemEntity.uniqueId.toString())
                val effectEntity = itemEntity.passengers[0]
                effectEntity.remove()
            }
        }
    }
    // 玩家捡起 清除特效
    @EventHandler
    fun onPlayerPickupItemEvent(e: PlayerPickupItemEvent) {
        val itemEntity = dropItemMap[e.item.uniqueId]
        if (itemEntity != null) {
            for (player in e.item.world.players) {
                PacketSender.removeParticle(player, itemEntity.uniqueId.toString())
                val effectEntity = itemEntity.passengers[0]
                effectEntity.remove()
            }
        }
    }
}