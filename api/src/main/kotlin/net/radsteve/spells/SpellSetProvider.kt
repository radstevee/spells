package net.radsteve.spells

public fun interface SpellSetProvider {
  public fun spells(): Set<SpellProvider>

  public fun init() {}
}
