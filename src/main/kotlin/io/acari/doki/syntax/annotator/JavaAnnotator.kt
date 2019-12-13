package io.acari.doki.syntax.annotator

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.find
import com.intellij.psi.PsiElement
import com.intellij.util.ObjectUtils.notNull
import io.acari.doki.util.toOptional
import java.util.*
import java.util.Optional.empty

class JavaAnnotator : BaseAnnotator() {
  companion object {
    val JAVA_KEYWORD: TextAttributesKey = notNull(
      find("JAVA_KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD
    )
    val MODIFIER: TextAttributesKey = createTextAttributesKey(
      "JAVA.MODIFIER",
      JAVA_KEYWORD
    )
    val STATIC_FINAL: TextAttributesKey = createTextAttributesKey(
      "JAVA.STATIC_FINAL",
      JAVA_KEYWORD
    )
    val THIS_SUPER: TextAttributesKey = createTextAttributesKey(
      "JAVA.THIS_SUPER",
      JAVA_KEYWORD
    )
  }

  override fun getKeywordType(element: PsiElement): Optional<TextAttributesKey> =
    when (element.text) {
      "private", "public", "protected" -> MODIFIER.toOptional()
      "static", "final" -> STATIC_FINAL.toOptional()
      "this", "super" -> THIS_SUPER.toOptional()
      else -> empty()
    }

}