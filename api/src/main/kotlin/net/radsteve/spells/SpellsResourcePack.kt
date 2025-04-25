package net.radsteve.spells

import net.radsteve.axi.ui.resource.pack.AxiPack
import net.radstevee.packed.core.asset.impl.ResourceAssetResolutionStrategy
import net.radstevee.packed.core.pack.ResourcePack
import net.radstevee.packed.core.pack.ResourcePackBuilder.Companion.resourcePack

public object SpellsResourcePack : AxiPack("spells") {
  override val pack: ResourcePack = resourcePack {
    meta {
      description = "Spells Resource Pack"
    }

    assetResolutionStrategy = ResourceAssetResolutionStrategy(javaClass)

    applyAxi()
  }
}
