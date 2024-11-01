package com.whitesoul.soulcore.util

import taboolib.common.platform.function.info
import taboolib.common.platform.function.submitAsync
import taboolib.common5.FileWatcher
import java.io.File

object AutoReloadFileUtil {
    val fileWatcher = FileWatcher()
    fun watcher(): FileWatcher {
        return fileWatcher
    }
}
