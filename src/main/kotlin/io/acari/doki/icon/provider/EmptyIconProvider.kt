package io.acari.doki.icon.provider

import com.intellij.icons.AllIcons
import com.intellij.ide.FileIconProvider
import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiBinaryFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiUtilCore
import io.acari.doki.config.ThemeConfig
import io.acari.doki.themes.ThemeManager
import io.acari.doki.util.toOptional
import javax.swing.Icon

class EmptyIconProvider : FileIconProvider, DumbAware {

  fun getIcon(element: PsiElement, flags: Int): Icon? =
    when (element) {
      is PsiBinaryFile -> getFileIcon(element)
      else -> null
    }

  private fun getFileIcon(element: PsiFile): Icon? {
    return ThemeManager.instance.currentTheme
      .flatMap { PsiUtilCore.getVirtualFile(element).toOptional() }
      .filter{ ThemeConfig.instance.isEmptyFiles }
      .map { VirtualFileInfo(element, it) }
      .map { AllIcons.FileTypes.Any_type }
      .orElseGet { null }
  }

  override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? {
    return AllIcons.FileTypes.Any_type
  }
}