package com.whitesoul.soulcore.file

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object FileModule {
    @Config("Module.yml")
    lateinit var file: ConfigFile
}