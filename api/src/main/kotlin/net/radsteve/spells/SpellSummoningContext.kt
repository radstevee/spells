package net.radsteve.spells

import org.bukkit.Location
import org.bukkit.entity.Player
import kotlin.time.Instant

public data class SpellSummoningContext(
  public val summoner: Player,
  public val location: Location,
  public val timestamp: Instant,
)
