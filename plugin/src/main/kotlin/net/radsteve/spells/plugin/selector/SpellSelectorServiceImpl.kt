package net.radsteve.spells.plugin.selector

import kotlinx.coroutines.flow.first
import net.kyori.adventure.text.Component.keybind
import net.radsteve.axi.ecs.get
import net.radsteve.axi.ecs.set
import net.radsteve.axi.ui.render.layer.RenderLayer
import net.radsteve.axi.ui.render.layer.addRenderable
import net.radsteve.axi.ui.render.layer.buildRenderLayer
import net.radsteve.axi.ui.render.renderState
import net.radsteve.axi.ui.text.buildText
import net.radsteve.spells.Spell
import net.radsteve.spells.SpellSelectorService
import net.radsteve.spells.Spells
import net.radsteve.spells.WandItem
import net.radsteve.spells.plugin.resource.Fonts
import net.radsteve.spells.plugin.resource.ItemSprites
import org.bukkit.Material
import org.bukkit.entity.Player

public class SpellSelectorServiceImpl : SpellSelectorService {
  override fun spellsOnHand(player: Player): List<Spell> {
    return Spells.collectEntries().take(5)
  }

  override fun renderSelector(spells: List<Spell>, player: Player): RenderLayer {
    return buildRenderLayer {
      spells.forEachIndexed { idx, spell ->
        val offset = 50 + (idx * 3)

        add {
          content(offset) {
            val slot = player.inventory.heldItemSlot
            val selectedIdx = slot - 1
            if (selectedIdx == idx) {
              append(spell.selectedSprite)
            } else {
              append(spell.sprite)
            }
          }
        }

        add {
          content(spell.sprite.width - 4) {
            append {
              append(keybind("key.hotbar.${idx + 2}"))
            }
            font(Fonts.UdonOffsets[10])
          }
        }
      }
    }
  }

  override suspend fun runSelector(
    spells: List<Spell>,
    player: Player,
  ): Spell {
    check(player.get<SelectorComponent>() == null) { "cannot run selector whilst player is in a selector" }

    val (_, flow) = player.set(SelectorComponent(spells))!!
    val renderLayer = renderSelector(spells, player)
    player.addRenderable(renderLayer)
    player.inventory.heldItemSlot = 0
    val inventoryContents = player.inventory.storageContents
    player.inventory.storageContents = inventoryContents
      .filter { item ->
        item?.let(WandItem::isWand) != false
      }
      .map { stack ->
        if (stack == null || stack.type == Material.AIR) {
          ItemSprites.EmptyItem
        } else {
          stack
        }
      }
      .toTypedArray()
    val selection = flow.first()

    player.renderState.currentlyRenderedRenderables.remove(renderLayer)
    if (player.renderState.currentlyRenderedRenderables.isEmpty()) {
      player.sendActionBar(buildText())
    }
    player.set<SelectorComponent>(null)
    player.inventory.storageContents = inventoryContents
    player.inventory.heldItemSlot = player.inventory.indexOfFirst(WandItem::isWand)

    return selection
  }
}
