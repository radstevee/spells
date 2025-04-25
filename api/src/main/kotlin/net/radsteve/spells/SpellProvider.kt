package net.radsteve.spells

public fun interface SpellProvider {
  public fun spell(): Spell

  public companion object {
    public operator fun invoke(provider: () -> Spell): SpellProvider {
      return object : SpellProvider by provider {}
    }
  }
}
