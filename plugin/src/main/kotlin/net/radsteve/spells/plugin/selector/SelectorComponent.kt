package net.radsteve.spells.plugin.selector

import kotlinx.coroutines.flow.MutableSharedFlow
import net.radsteve.spells.Spell

public data class SelectorComponent(
  public val choices: List<Spell>,
  public val flow: MutableSharedFlow<Spell> = MutableSharedFlow(),
)
