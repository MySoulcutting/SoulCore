package com.whitesoul.soulcore.command

import com.whitesoul.soulcore.file.FileDropItemEffect
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand
import taboolib.module.chat.colored

object ReloadCommand {
    val command = subCommand {
        execute<CommandSender> { sender, context, argument ->
            // 掉落物特效
            FileDropItemEffect.file.reload()
            FileDropItemEffect.dropItemEffect.clear()
            FileDropItemEffect.load()
            sender.sendMessage("&6所有配置文件成功完成！".colored())
        }
    }
}