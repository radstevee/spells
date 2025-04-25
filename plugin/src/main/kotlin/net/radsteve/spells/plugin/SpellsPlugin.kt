package net.radsteve.spells.plugin

import net.radsteve.axi.coroutines.registerEventListeners
import net.radsteve.axi.plugin.AxiPlugin
import net.radsteve.spells.SpellSetProvider
import net.radsteve.spells.Spells
import net.radsteve.spells.SpellsResourcePack
import net.radsteve.spells.internal.CompositePluginClassLoader
import net.radsteve.spells.plugin.resource.Fonts
import net.radsteve.spells.plugin.resource.ItemSprites
import net.radsteve.spells.plugin.selector.SelectorHandler
import java.util.ServiceLoader

public object SpellsPlugin : AxiPlugin() {
  override suspend fun enable() {
    val compositeCl = CompositePluginClassLoader()
    val serviceLoader = ServiceLoader.load(SpellSetProvider::class.java, compositeCl)
    val spellSetProviders = serviceLoader.toList()
    spellSetProviders.forEach(SpellSetProvider::init)
    val spells = spellSetProviders.flatMap(SpellSetProvider::spells)
    spells.forEach(Spells::register)

    slF4JLogger.info("Spells found: ${Spells.collectKeys()}")

    Fonts
    ItemSprites

    SpellsResourcePack.save()

    registerEventListeners(
      ConnectionHandler,
      WandHandler,
      SelectorHandler,
    )
  }
}
