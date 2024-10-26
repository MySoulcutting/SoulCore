package com.whitesoul.soulcore

import com.whitesoul.soulcore.module.Module
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.platform.BukkitPlugin
import taboolib.platform.util.bukkitPlugin

object SoulCorePlugin : Plugin() {

    override fun onEnable() {
        info("开始加载功能")
        Module.register(bukkitPlugin)
        info("功能加载完成")
        info("成功加载 SoulCore")
        info("版本: ${BukkitPlugin.getInstance().description.version}")
        info("作者: WhiteSoul")
    }
}