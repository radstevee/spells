package net.radsteve.spells.spells

import net.radsteve.axi.ui.resource.sprite.Sprite
import net.radsteve.axi.ui.resource.sprite.SpriteRegistry
import net.radsteve.spells.SpellsResourcePack
import net.radstevee.packed.core.key.Key

public object DefaultSpellsSprites : SpriteRegistry() {
  private fun registerSprite(key: String, scaling: Float = 0.5f): Sprite {
    return registerSprite(Key("spells", "spells/$key.png"), SpellsResourcePack, scaling)
  }

  public val TestSpellOne: Sprite = registerSprite("test_one")
  public val TestSpellOneSelected: Sprite = registerSprite("selected/test_one")

  public val TestSpellTwo: Sprite = registerSprite("test_two")
  public val TestSpellTwoSelected: Sprite = registerSprite("selected/test_two")
}
