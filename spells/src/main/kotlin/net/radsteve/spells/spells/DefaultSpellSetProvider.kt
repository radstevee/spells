package net.radsteve.spells.spells

import net.radsteve.spells.SpellProvider
import net.radsteve.spells.SpellSetProvider

public class DefaultSpellSetProvider : SpellSetProvider {
  override fun spells(): Set<SpellProvider> {
    return setOf(
      SpellProvider(::TestSpellOne),
      SpellProvider(::TestSpellTwo),
    )
  }

  override fun init() {
    DefaultSpellsSprites // Initialise our sprites
  }
}
