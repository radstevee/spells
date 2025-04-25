package net.radsteve.spells.spells

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Key.key
import net.kyori.adventure.text.Component
import net.radsteve.axi.ui.text.send
import net.radsteve.axi.ui.text.text
import net.radsteve.spells.Spell
import net.radsteve.spells.SpellSummoningContext
import net.radsteve.spells.particle.raytraceParticleUntilBlock
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.data.type.TNT
import org.bukkit.entity.TNTPrimed
import org.bukkit.util.Vector

public class TestSpellOne : Spell {
  override val id: Key = key("spells", "spells/test_one")
  override val displayName: Component = text("Test Spell One")

  override suspend fun execute(ctx: SpellSummoningContext) {
    ctx.summoner.send("You summoned spell one :D")

    val block = raytraceParticleUntilBlock(
      ctx.summoner.eyeLocation,
      ctx.summoner.eyeLocation.direction,
      Particle.DUST,
      5,
      maxOffset = Vector(0.25, 0.25, 0.25),
      data = Particle.DustOptions(Color.FUCHSIA, 1f)
    ) ?: return

    block.world.spawn(block.location.add(0.5, 0.5, 0.5), TNTPrimed::class.java) { tnt ->
      tnt.fuseTicks = 0
    }
  }
}
