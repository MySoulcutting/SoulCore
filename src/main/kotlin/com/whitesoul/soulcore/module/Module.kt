package com.whitesoul.soulcore.module

import com.whitesoul.soulcore.file.FileModule
import com.whitesoul.soulcore.module.autorespawn.AutoRespawnListener
import com.whitesoul.soulcore.module.dropitemeffect.DropItemEffectListener
import com.whitesoul.soulcore.module.lightkungfu.LightKungFuListener
import taboolib.common.platform.function.info
import taboolib.module.chat.colored
import taboolib.platform.BukkitPlugin


object Module {
    // 注册模块
    fun register(plugin: BukkitPlugin) {
        if (FileModule.file.getBoolean("Module.AutoRespawn")) {
            BukkitPlugin.getInstance().server.pluginManager.registerEvents(AutoRespawnListener, plugin)
            info("&e自动复活 &a已开启".colored())
        } else {
            info("&e自动复活 &c未开启".colored())
        }
        if (FileModule.file.getBoolean("Module.LightKungFu")) {
            BukkitPlugin.getInstance().server.pluginManager.registerEvents(LightKungFuListener, plugin)
            info("&e轻功 &a已开启".colored())
        } else {
            info("&e轻功 &c未开启".colored())
        }
        if (FileModule.file.getBoolean("Module.DropItemEffect")) {
            BukkitPlugin.getInstance().server.pluginManager.registerEvents(DropItemEffectListener, plugin)
            info("&e掉落物特效 &a已开启".colored())
        } else {
            info("&e掉落物特效 &c未开启".colored())
        }
    }


}