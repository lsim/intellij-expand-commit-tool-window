package com.github.ivw.expandcommittoolwindow

import com.intellij.ide.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.wm.*
import com.intellij.openapi.wm.ex.*
import com.intellij.ui.content.ContentManagerEvent
import com.intellij.ui.content.ContentManagerListener

class ExpandCommitToolWindowListener : ToolWindowManagerListener {
  override fun toolWindowShown(toolWindow: ToolWindow) {
    if (toolWindow.id == "Commit") {
      expandCommitTree(toolWindow)
      setupContentChangeListener(toolWindow)
    }
  }

  override fun stateChanged(toolWindowManager: ToolWindowManager) {
    val commitToolWindow = toolWindowManager.getToolWindow("Commit")
    if (commitToolWindow?.isActive == true) {
      expandCommitTree(commitToolWindow)
    }
  }

  private fun expandCommitTree(toolWindow: ToolWindow) {
    ApplicationManager.getApplication().invokeLater {
      toolWindow.contentManager.contents.getOrNull(0)?.preferredFocusableComponent
        ?.let { DataManager.getInstance().getDataContext(it) }
        ?.getData(PlatformDataKeys.TREE_EXPANDER)
        ?.expandAll()
    }
  }

  private fun setupContentChangeListener(toolWindow: ToolWindow) {
    toolWindow.contentManager.addContentManagerListener(object : ContentManagerListener {
      override fun contentAdded(event: ContentManagerEvent) {
        ApplicationManager.getApplication().invokeLater {
          expandCommitTree(toolWindow)
        }
      }

      override fun selectionChanged(event: ContentManagerEvent) {
        ApplicationManager.getApplication().invokeLater {
          expandCommitTree(toolWindow)
        }
      }
    })
  }
}
