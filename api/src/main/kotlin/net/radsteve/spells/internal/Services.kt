package net.radsteve.spells.internal

import java.util.ServiceLoader

@InternalSpellsApi
public object Services {
  @PublishedApi
  internal val cache: MutableMap<Class<out Any>, Any> = mutableMapOf()

  public inline fun <reified T : Any> get(): T {
    val cached = cache[T::class.java]
    if (cached != null) {
      return cached as T
    }

    val service = ServiceLoader.load(T::class.java, CompositePluginClassLoader()).first()
    cache[T::class.java] = service
    return service
  }
}
