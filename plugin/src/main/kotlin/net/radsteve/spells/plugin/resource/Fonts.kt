package net.radsteve.spells.plugin.resource

import net.radsteve.axi.ui.resource.font.AxiFontRegistry
import net.radsteve.axi.ui.resource.font.AxiOffsetFonts
import net.radsteve.axi.ui.resource.font.StoredTruetypeFont
import net.radsteve.spells.SpellsResourcePack
import net.radsteve.spells.plugin.util.resourceFile

public object Fonts : AxiFontRegistry() {
  public val Udon: StoredTruetypeFont = registerStoredTtf(
    "udon",
    "spells",
    resourceFile("assets/spells/font/udon.ttf", ".ttf"),
    SpellsResourcePack,
    scaling = 0.5f,
  )
  public val UdonOffsets: AxiOffsetFonts = registerOffsets(Udon, SpellsResourcePack)
}
