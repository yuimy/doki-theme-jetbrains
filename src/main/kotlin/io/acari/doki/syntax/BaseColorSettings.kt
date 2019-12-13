package io.acari.doki.syntax

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.psi.codeStyle.DisplayPrioritySortable


abstract class BaseColorSettings : ColorSettingsPage, DisplayPrioritySortable {
  companion object {
    fun fetchSyntaxHighlighter(language: Language): SyntaxHighlighter =
      SyntaxHighlighterFactory.getSyntaxHighlighter(language, null, null)
        ?: SyntaxHighlighterFactory.getSyntaxHighlighter(Language.ANY, null, null)
  }
}