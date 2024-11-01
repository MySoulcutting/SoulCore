package com.whitesoul.soulcore.command

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper

@CommandHeader(name = "soulcore", aliases = ["sc"], permission = "soulcore.command")
object MainCommand {
    val main = mainCommand {
        createHelper()
    }
    @CommandBody
    val reload = ReloadCommand.command
    }