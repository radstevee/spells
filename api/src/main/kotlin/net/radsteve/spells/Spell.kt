package net.radsteve.spells

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Key.key
import net.kyori.adventure.text.Component
import net.radsteve.axi.ui.resource.sprite.Sprite
import net.radsteve.axi.ui.resource.sprite.SpriteRegistry
import net.radsteve.axi.ui.util.packed

public interface Spell {
  public val id: Key
  public val displayName: Component
  public val sprite: Sprite get() {
    val spriteKey = id.packed().copy(value = id.value() + ".png")
    return SpriteRegistry.AxiSprites[spriteKey] ?: error("sprite $spriteKey could not be found for spell $id")
  }
  public val selectedSprite: Sprite get() {
    val idValue = id.value()
    val lastSlashIdx = idValue.lastIndexOf('/')
    val path = idValue.substring(0, lastSlashIdx)
    val name = idValue.substring(lastSlashIdx + 1)
    val newValue = "$path/selected/$name.png"
    val spriteKey = key(id.namespace(), newValue).packed()

    return SpriteRegistry.AxiSprites[spriteKey] ?: error("sprite $spriteKey could not be found for spell $id")
  }

  public suspend fun execute(ctx: SpellSummoningContext) {}
}
