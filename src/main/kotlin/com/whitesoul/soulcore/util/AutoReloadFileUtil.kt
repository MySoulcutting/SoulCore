package com.whitesoul.soulcore.util

import taboolib.common5.FileWatcher

object AutoReloadFileUtil {
    val fileWatcher = FileWatcher()
    fun watcher(): FileWatcher {
        return fileWatcher
    }
}
