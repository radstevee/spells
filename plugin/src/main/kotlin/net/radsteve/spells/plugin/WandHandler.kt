package net.radsteve.spells.plugin

import kotlinx.coroutines.launch
import net.radsteve.axi.ecs.get
import net.radsteve.axi.event.SuspendingListener
import net.radsteve.axi.ui.text.send
import net.radsteve.spells.SpellSelectorService
import net.radsteve.spells.SpellSummoningContext
import net.radsteve.spells.WandItem
import net.radsteve.spells.plugin.selector.SelectorComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.time.Clock

public object WandHandler : SuspendingListener {
  @EventHandler
  private fun on(event: PlayerInteractEvent) {
    if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) {
      return
    }

    val player = event.player
    val item = event.item ?: return
    if (!WandItem.isWand(item)) {
      return
    }

    if (player.get<SelectorComponent>() != null) {
      return
    }

    launch {
      val spell = SpellSelectorService.runSelector(SpellSelectorService.spellsOnHand(player), player)
      player.send("Spell: ", spell.displayName)
      spell.execute(SpellSummoningContext(player, player.location, Clock.System.now()))
    }
  }
}
