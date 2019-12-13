package io.acari.doki.syntax

import com.intellij.icons.AllIcons
import com.intellij.lang.Language.ANY
import com.intellij.lang.Language.findLanguageByID
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.colors.CodeInsightColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.find
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor.EMPTY_ARRAY
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.util.ObjectUtils.notNull
import com.intellij.util.PlatformUtils.isIntelliJ
import gnu.trove.THashMap
import io.acari.doki.syntax.annotator.JavaAnnotator
import javax.swing.Icon

class JavaColorSettings : BaseColorSettings() {
  companion object {
    private val JAVA_KEYWORD: TextAttributesKey = JavaAnnotator.JAVA_KEYWORD
    private val THIS_SUPER: TextAttributesKey = JavaAnnotator.THIS_SUPER
    private val MODIFIER: TextAttributesKey = JavaAnnotator.MODIFIER
    private val STATIC_FINAL: TextAttributesKey = JavaAnnotator.STATIC_FINAL

    private val JAVA_ATTRIBUTES: Array<AttributesDescriptor> = arrayOf(
      AttributesDescriptor("Keywords: this, super", JavaAnnotator.THIS_SUPER),
      AttributesDescriptor("Keywords: private, public, protected", JavaAnnotator.MODIFIER),
      AttributesDescriptor("Keywords: static, final", JavaAnnotator.STATIC_FINAL)
    )

    private val JAVA_DESCRIPTORS: Map<String, TextAttributesKey> =
      createJavaDescriptors()

    private fun createJavaDescriptors(): Map<String, TextAttributesKey> {
      val descriptors: MutableMap<String, TextAttributesKey> =
        THashMap()

      descriptors["field"] = notNull(
        find("INSTANCE_FIELD_ATTRIBUTES"),
        INSTANCE_FIELD
      )
      descriptors["unusedField"] = CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES
      descriptors["error"] = CodeInsightColors.ERRORS_ATTRIBUTES
      descriptors["warning"] = CodeInsightColors.WARNINGS_ATTRIBUTES
      descriptors["weak_warning"] = CodeInsightColors.WEAK_WARNING_ATTRIBUTES
      descriptors["server_problems"] = CodeInsightColors.GENERIC_SERVER_ERROR_OR_WARNING
      descriptors["server_duplicate"] = CodeInsightColors.DUPLICATE_FROM_SERVER
      descriptors["unknownType"] = CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES
      descriptors["localVar"] = notNull(
        find("LOCAL_VARIABLE_ATTRIBUTES"),
        LOCAL_VARIABLE
      )
      descriptors["reassignedLocalVar"] = notNull(
        find("REASSIGNED_LOCAL_VARIABLE_ATTRIBUTES"),
        REASSIGNED_LOCAL_VARIABLE
      )
      descriptors["reassignedParameter"] = notNull(
        find("REASSIGNED_PARAMETER_ATTRIBUTES);"),
        REASSIGNED_PARAMETER
      )
      descriptors["implicitAnonymousParameter"] = notNull(
        find("IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES);"),
        CLASS_NAME
      )
      descriptors["static"] = notNull(
        find("STATIC_FIELD_ATTRIBUTES);"),
        STATIC_FIELD
      )
      descriptors["static_final"] = notNull(
        find("STATIC_FINAL_FIELD_ATTRIBUTES);"),
        STATIC_FIELD
      )
      descriptors["deprecated"] = CodeInsightColors.DEPRECATED_ATTRIBUTES
      descriptors["for_removal"] = CodeInsightColors.MARKED_FOR_REMOVAL_ATTRIBUTES
      descriptors["constructorCall"] = notNull(
        find("CONSTRUCTOR_CALL_ATTRIBUTES);"),
        FUNCTION_CALL
      )
      descriptors["constructorDeclaration"] = notNull(
        find("CONSTRUCTOR_DECLARATION_ATTRIBUTES);"),
        FUNCTION_DECLARATION
      )
      descriptors["methodCall"] = notNull(
        find("METHOD_CALL_ATTRIBUTES);"),
        FUNCTION_CALL
      )
      descriptors["methodDeclaration"] = notNull(
        find("METHOD_DECLARATION_ATTRIBUTES);"),
        FUNCTION_DECLARATION
      )
      descriptors["static_method"] = notNull(
        find("STATIC_METHOD_ATTRIBUTES);"),
        STATIC_METHOD
      )
      descriptors["abstract_method"] = notNull(
        find("ABSTRACT_METHOD_ATTRIBUTES);"),
        FUNCTION_CALL
      )
      descriptors["inherited_method"] = notNull(
        find("INHERITED_METHOD_ATTRIBUTES);"),
        FUNCTION_CALL
      )
      descriptors["param"] = notNull(
        find("PARAMETER_ATTRIBUTES);"),
        PARAMETER
      )
      descriptors["lambda_param"] = notNull(
        find("LAMBDA_PARAMETER_ATTRIBUTES);"),
        PARAMETER
      )
      descriptors["class"] = notNull(
        find("CLASS_NAME_ATTRIBUTES);"),
        CLASS_NAME
      )
      descriptors["anonymousClass"] = notNull(
        find("ANONYMOUS_CLASS_NAME_ATTRIBUTES);"),
        CLASS_NAME
      )
      descriptors["typeParameter"] = notNull(
        find("TYPE_PARAMETER_NAME_ATTRIBUTES);"),
        PARAMETER
      )
      descriptors["abstractClass"] = notNull(
        find("ABSTRACT_CLASS_NAME_ATTRIBUTES);"),
        CLASS_NAME
      )
      descriptors["interface"] = notNull(
        find("INTERFACE_NAME_ATTRIBUTES);"),
        INTERFACE_NAME
      )
      descriptors["enum"] = notNull(
        find("ENUM_NAME_ATTRIBUTES);"),
        CLASS_NAME
      )
      descriptors["annotationName"] = notNull(
        find("ANNOTATION_NAME_ATTRIBUTES);"),
        METADATA
      )
      descriptors["annotationAttributeName"] = notNull(
        find("ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES);"),
        METADATA
      )
      descriptors["javadocTagValue"] = notNull(
        find("DOC_COMMENT_TAG_VALUE);"),
        DOC_COMMENT_TAG_VALUE
      )
      descriptors["instanceFinalField"] = notNull(
        find("INSTANCE_FINAL_FIELD_ATTRIBUTES);"),
        INSTANCE_FIELD
      )
      descriptors["staticallyConstImported"] = notNull(
        find("STATIC_FINAL_FIELD_IMPORTED_ATTRIBUTES);"),
        STATIC_FIELD
      )
      descriptors["staticallyImported"] = notNull(
        find("STATIC_FIELD_IMPORTED_ATTRIBUTES);"),
        STATIC_FIELD
      )
      descriptors["static_imported_method"] = notNull(
        find("STATIC_METHOD_CALL_IMPORTED_ATTRIBUTES);"),
        STATIC_METHOD
      )

      descriptors["keyword"] = JAVA_KEYWORD
      descriptors["this"] = THIS_SUPER
      descriptors["sf"] = STATIC_FINAL
      descriptors["modifier"] = MODIFIER
      return descriptors
    }
  }

  override fun getHighlighter(): SyntaxHighlighter =
    fetchSyntaxHighlighter(
      findLanguageByID("JAVA") ?: ANY
    )

  override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> =
    JAVA_DESCRIPTORS.toMutableMap()

  override fun getIcon(): Icon? =
    AllIcons.FileTypes.Java

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> =
    JAVA_ATTRIBUTES

  override fun getColorDescriptors(): Array<ColorDescriptor> =
    EMPTY_ARRAY

  override fun getDisplayName(): String =
    "Java Additions"

  override fun getDemoText(): String =
    """public class <class>SomeClass</class> extends <class>BaseClass</class> {
  <modifier>private</modifier> <sf>static final</sf> <field>field</field> = null;
  <modifier>protected</modifier> <sf>final</sf> <field>otherField</field>;

  <modifier>public</modifier> <constructorDeclaration>SomeClass</constructorDeclaration>(<interface>AnInterface</interface> <param>param1</param>, int[] <reassignedParameter>reassignedParam</reassignedParameter>,
                  int <param>param2</param>
                  int <param>param3</param>) {
    <this>super</this>(<param>param1</param>);
    <this>this</this>.<warning>field</warning> = <param>param1</param>;
  }
 }
"""

  override fun getPriority(): DisplayPriority =
    if (isIntelliJ()) DisplayPriority.KEY_LANGUAGE_SETTINGS
    else DisplayPriority.LANGUAGE_SETTINGS

}