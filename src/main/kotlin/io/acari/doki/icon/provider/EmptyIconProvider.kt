package io.acari.doki.icon.provider

import com.intellij.icons.AllIcons
import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiUtilCore
import io.acari.doki.config.ThemeConfig
import io.acari.doki.themes.ThemeManager
import io.acari.doki.util.toOptional
import javax.swing.Icon

class EmptyIconProvider : IconProvider(),
  DumbAware {

  override fun getIcon(element: PsiElement, flags: Int): Icon? =
    when (element) {
      is PsiFile -> getFileIcon(element)
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
}