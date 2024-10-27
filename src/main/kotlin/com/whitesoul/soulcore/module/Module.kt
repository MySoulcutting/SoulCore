package com.whitesoul.soulcore.module

import com.whitesoul.soulcore.file.FileModule
import com.whitesoul.soulcore.module.autorespawn.AutoRespawnListener
import com.whitesoul.soulcore.module.dropitemeffect.DropItemEffectListener
import com.whitesoul.soulcore.module.lightkungfu.LightKungFuListener
import ink.ptms.adyeshach.core.entity.path.PathFinderHandler
import ink.ptms.adyeshach.core.entity.path.PathType
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Pig
import org.bukkit.entity.Player
import org.bukkit.entity.Wolf
import org.bukkit.util.Vector
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.command.simpleCommand
import taboolib.common.platform.function.dev
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submit
import taboolib.common.util.random
import taboolib.module.chat.colored
import taboolib.module.effect.createPolarEquationRenderer
import taboolib.module.navigation.NodeEntity
import taboolib.module.navigation.NodeReader
import taboolib.module.navigation.PathFinder
import taboolib.module.navigation.createPathfinder
import taboolib.platform.BukkitPlugin
import java.util.*


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