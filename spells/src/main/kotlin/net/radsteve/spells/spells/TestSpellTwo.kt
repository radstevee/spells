package net.radsteve.spells.spells

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Key.key
import net.kyori.adventure.text.Component
import net.radsteve.axi.ui.text.send
import net.radsteve.axi.ui.text.text
import net.radsteve.spells.Spell
import net.radsteve.spells.SpellSummoningContext

public class TestSpellTwo : Spell {
  override val id: Key = key("spells", "spells/test_two")
  override val displayName: Component = text("Test Spell Two")

  override suspend fun execute(ctx: SpellSummoningContext) {
    ctx.summoner.send("You summoned spell two :D")
  }
}
