package io.acari.doki.syntax.annotator

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.util.ObjectUtils
import java.util.*

class JavaAnnotator : BaseAnnotator() {
  companion object {
    val JAVA_KEYWORD = ObjectUtils.notNull(
      TextAttributesKey.find("JAVA_KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD
    )
    val MODIFIER = TextAttributesKey.createTextAttributesKey(
      "JAVA.MODIFIER",
      JAVA_KEYWORD
    )
    val STATIC_FINAL = TextAttributesKey.createTextAttributesKey(
      "JAVA.STATIC_FINAL",
      JAVA_KEYWORD
    )
    val THIS_SUPER = TextAttributesKey.createTextAttributesKey(
      "JAVA.THIS_SUPER",
      JAVA_KEYWORD
    )
  }

  override fun getKeywordType(element: PsiElement): Optional<TextAttributesKey> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}