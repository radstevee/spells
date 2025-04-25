package net.radsteve.spells

import net.kyori.adventure.key.Key
import net.radsteve.axi.Registry

public object Spells : Registry<Key, Spell>() {
  public fun register(provider: SpellProvider): Spell {
    val spell = provider.spell()
    register(spell.id, spell)
    return spell
  }
}
