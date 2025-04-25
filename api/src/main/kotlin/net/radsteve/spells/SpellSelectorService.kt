package net.radsteve.spells

import net.radsteve.axi.ui.render.layer.RenderLayer
import net.radsteve.spells.internal.Services
import org.bukkit.entity.Player

public interface SpellSelectorService {
  public fun spellsOnHand(player: Player): List<Spell>
  public fun renderSelector(spells: List<Spell>, player: Player): RenderLayer
  public suspend fun runSelector(spells: List<Spell>, player: Player): Spell

  public companion object : SpellSelectorService by Services.get()
}
