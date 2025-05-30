package com.github.ivw.expandcommittoolwindow

import com.intellij.ide.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.wm.*
import com.intellij.openapi.wm.ex.*

class ExpandCommitToolWindowListener : ToolWindowManagerListener {
  override fun toolWindowShown(toolWindow: ToolWindow) {
    if (toolWindow.id == "Commit") {
      toolWindow.contentManager.contents.getOrNull(0)?.preferredFocusableComponent
        ?.let { DataManager.getInstance().getDataContext(it) }
        ?.getData(PlatformDataKeys.TREE_EXPANDER)
        ?.expandAll()
    }
  }
}