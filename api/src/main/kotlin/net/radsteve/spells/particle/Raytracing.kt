package net.radsteve.spells.particle

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.util.Vector

public fun raytraceParticleUntilBlock(
  start: Location,
  headRotation: Vector,
  particle: Particle,
  count: Int,
  maxOffset: Vector = Vector(),
  maxDistance: Double = 256.0,
  particleSpacing: Double = 0.5,
  speed: Double = 0.0,
): Block? {
  return raytraceParticleUntilBlock<Any>(start, headRotation, particle, count, maxOffset, maxDistance, particleSpacing, speed)
}

public fun <T> raytraceParticleUntilBlock(
  start: Location,
  headRotation: Vector,
  particle: Particle,
  count: Int,
  maxOffset: Vector = Vector(),
  maxDistance: Double = 256.0,
  particleSpacing: Double = 0.5,
  speed: Double = 0.0,
  data: T? = null,
): Block? {
  val result = start.world.rayTraceBlocks(start, headRotation, maxDistance)
  val block = result?.hitBlock
  val hitPos = result?.hitPosition ?: start
    .toVector()
    .add(
      Vector(
        headRotation.x * maxDistance,
        headRotation.y * maxDistance,
        headRotation.z * maxDistance
      )
    )

  val hitLocation = Location(start.world, hitPos.x, hitPos.y, hitPos.z)
  val distance = start.distance(hitLocation)
  var dist = 0.0
  while (dist < distance) {
    val x = start.x + headRotation.x * dist
    val y = start.y + headRotation.y * dist
    val z = start.z + headRotation.z * dist

    val location = Location(start.world, x, y, z)

    if (dist <= particleSpacing * 2) {
      dist += particleSpacing
      continue
    }

    start.world.spawnParticle(
      particle,
      location,
      count,
      maxOffset.x,
      maxOffset.y,
      maxOffset.z,
      speed,
      data
    )

    dist += particleSpacing
  }

  return block
}
