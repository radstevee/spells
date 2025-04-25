package net.radsteve.spells.internal

import net.radsteve.axi.plugin.AxiPluginHolder
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.lang.reflect.Field
import java.net.URL
import java.net.URLClassLoader

@InternalSpellsApi
public class CompositePluginClassLoader : URLClassLoader(collectURLs(), AxiPluginHolder.plugin().classLoader()) {
  @InternalSpellsApi
  public companion object {
    private val classLoaderField: Field by lazy {
      JavaPlugin::class.java
        .getDeclaredField("classLoader")
        .also(Field::trySetAccessible)
    }

    public fun JavaPlugin.classLoader(): URLClassLoader {
      return classLoaderField.get(this) as URLClassLoader
    }

    public fun collectURLs(): Array<URL> {
      return Bukkit.getPluginManager()
        .plugins
        .filterIsInstance<JavaPlugin>()
        .flatMap { plugin -> plugin.classLoader().urLs.toList() }
        .toTypedArray()
    }
  }
}
