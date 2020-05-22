package io.acari.doki.syntax.annotator

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import java.util.*

abstract class BaseAnnotator : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    if (element is LeafPsiElement &&
      PsiTreeUtil.getParentOfType(element, PsiComment::class.java) !== null) {
      getKeywordType(element)
        .ifPresent {
          val textRange = element.getTextRange()
          val range = TextRange(textRange.startOffset, textRange.endOffset)
          val annotation = holder.createAnnotation(
            HighlightSeverity.INFORMATION, range, null
          )
          annotation.textAttributes = it
        }
    }
  }

  protected abstract fun getKeywordType(element: PsiElement): Optional<TextAttributesKey>
}