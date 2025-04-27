package net.radsteve.spells.internal

@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR,
  message = """
This API is internal to spells and has zero guarantee of persisting across versions.
  """,
)
public annotation class InternalSpellsApi
