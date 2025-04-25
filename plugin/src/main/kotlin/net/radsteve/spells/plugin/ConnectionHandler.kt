package net.radsteve.spells.plugin

import net.radsteve.axi.event.SuspendingListener
import net.radsteve.axi.ui.resource.pack.send.sendAxiPack
import net.radsteve.spells.SpellsResourcePack
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

public object ConnectionHandler : SuspendingListener {
  @EventHandler
  private suspend fun on(event: PlayerJoinEvent) {
    event.player.sendAxiPack(SpellsResourcePack)
  }
}
