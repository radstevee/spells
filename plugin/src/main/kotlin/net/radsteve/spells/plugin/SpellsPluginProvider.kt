package net.radsteve.spells.plugin

import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.bootstrap.PluginProviderContext
import org.bukkit.plugin.java.JavaPlugin

internal class SpellsPluginProvider : PluginBootstrap {
  override fun bootstrap(context: BootstrapContext) {
  }

  override fun createPlugin(context: PluginProviderContext): JavaPlugin {
    return SpellsPlugin
  }
}
