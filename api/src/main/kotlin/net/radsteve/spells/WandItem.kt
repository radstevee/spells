package net.radsteve.spells

import net.radsteve.axi.utility.buildItemStack
import net.radsteve.axi.utility.editItemMeta
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

public class WandItem {
  public val item: ItemStack = buildItemStack(Material.CARROT_ON_A_STICK) {
    editItemMeta {
      persistentDataContainer.set(PDC_KEY, PersistentDataType.BYTE, 0)
    }
  }

  public companion object {
    public val PDC_KEY: NamespacedKey = NamespacedKey("spells", "wand_item")

    public fun isWand(item: ItemStack): Boolean {
      return item.persistentDataContainer.has(PDC_KEY)
    }
  }
}
