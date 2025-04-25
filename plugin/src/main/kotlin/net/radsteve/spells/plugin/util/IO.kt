package net.radsteve.spells.plugin.util

import net.radsteve.axi.utility.resource
import net.radsteve.spells.internal.CompositePluginClassLoader.Companion.classLoader
import net.radsteve.spells.plugin.SpellsPlugin
import java.io.File
import kotlin.io.path.createTempFile
import kotlin.io.path.writeBytes

public fun resourceFile(path: String, ext: String? = null): File {
  val file = createTempFile(suffix = ext)
  val bytes = resource<ByteArray>(path, SpellsPlugin.classLoader())
  file.writeBytes(bytes)

  return file.toFile()
}
