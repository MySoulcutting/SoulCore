package com.whitesoul.soulcore.file

import com.whitesoul.soulcore.module.dropitemeffect.DropItem
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object FileDropItemEffect {
    @Config("DropItemEffect.yml")
    lateinit var file: ConfigFile
    val dropItemEffect = HashMap<String, DropItem>()
    @Awake(LifeCycle.LOAD)
    fun load() {
        val sec = file.getConfigurationSection("ItemEffect")?.getKeys(false)
        if (sec != null) {
            for (key in sec) {
                val itemName = file.getString("ItemEffect.$key.ItemName")!!
                val effectPath = file.getString("ItemEffect.$key.EffectPath")!!
                val time = file.getInt("ItemEffect.$key.Time")
                dropItemEffect[itemName] = DropItem(itemName, effectPath, time)
            }
        }
    }
}