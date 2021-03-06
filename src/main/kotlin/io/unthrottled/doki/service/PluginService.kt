package io.unthrottled.doki.service

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import io.unthrottled.doki.util.toOptional

const val MOTIVATOR_PLUGIN_ID = "zd.zero.waifu-motivator-plugin"
const val AMII_PLUGIN_ID = "io.unthrottled.amii"

object PluginService {

  fun isMotivatorInstalled(): Boolean = PluginManagerCore.isPluginInstalled(
    PluginId.getId(MOTIVATOR_PLUGIN_ID)
  )

  fun isAmiiInstalled(): Boolean = PluginManagerCore.isPluginInstalled(
    PluginId.getId(AMII_PLUGIN_ID)
  )

  fun canAmiiBeInstalled(): Boolean =
    PluginManagerCore.getPlugin(PluginId.getId(AMII_PLUGIN_ID)).toOptional()
      .filter { PluginManagerCore.isCompatible(it) }
      .isPresent
}
