package net.radsteve.spells.plugin

import net.radsteve.axi.command.AutoRegistered
import net.radsteve.axi.command.Command
import net.radsteve.spells.WandItem

@AutoRegistered
public val WandCommand: Command = Command("wand") {
  playerExecutor { player ->
    player.give(WandItem().item)
  }
}
