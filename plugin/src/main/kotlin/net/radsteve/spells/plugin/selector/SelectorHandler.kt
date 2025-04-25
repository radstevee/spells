package net.radsteve.spells.plugin.selector

import net.radsteve.axi.ecs.get
import net.radsteve.axi.event.SuspendingListener
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

public object SelectorHandler : SuspendingListener {
  @EventHandler
  private suspend fun on(event: PlayerInteractEvent) {
    if (event.action !in setOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) {
      return
    }

    val player = event.player
    val selector = player.get<SelectorComponent>() ?: return
    val slot = player.inventory.heldItemSlot
    val selectedIdx = slot - 1
    val selected = selector.choices.getOrNull(selectedIdx) ?: return
    selector.flow.emit(selected)
  }
}
