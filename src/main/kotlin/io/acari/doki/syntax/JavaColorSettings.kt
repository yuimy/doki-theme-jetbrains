package io.acari.doki.syntax

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.psi.codeStyle.DisplayPriority
import gnu.trove.THashMap
import io.acari.doki.syntax.annotator.JavaAnnotator
import javax.swing.Icon

class JavaColorSettings : BaseColorSettings() {
  companion object {
    private val JAVA_DESCRIPTORS: Map<String, TextAttributesKey> =
      THashMap()

    private val JAVA_KEYWORD: TextAttributesKey = JavaAnnotator.JAVA_KEYWORD
    private val THIS_SUPER: TextAttributesKey = JavaAnnotator.THIS_SUPER
    private val MODIFIER: TextAttributesKey = JavaAnnotator.MODIFIER
    private val STATIC_FINAL: TextAttributesKey = JavaAnnotator.STATIC_FINAL
  }
  override fun getHighlighter(): SyntaxHighlighter {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getIcon(): Icon? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getColorDescriptors(): Array<ColorDescriptor> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getDisplayName(): String {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getDemoText(): String {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getPriority(): DisplayPriority {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}