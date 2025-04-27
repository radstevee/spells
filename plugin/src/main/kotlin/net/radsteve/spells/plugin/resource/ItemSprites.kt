package net.radsteve.spells.plugin.resource

import net.radsteve.axi.ui.text.buildText
import net.radsteve.axi.ui.util.bukkit
import net.radsteve.axi.utility.buildItemStack
import net.radsteve.axi.utility.editItemMeta
import net.radsteve.spells.SpellsResourcePack
import net.radstevee.packed.core.item.ItemModel
import net.radstevee.packed.core.item.definition.BasicItem
import net.radstevee.packed.core.item.definition.ItemDefinition
import net.radstevee.packed.core.key.Key
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

public object ItemSprites {
  public fun apply(model: ItemDefinition, to: ItemStack) {
    to.editItemMeta {
      itemModel = model.key.bukkit()
    }
  }

  public val EmptyModel: ItemModel = SpellsResourcePack.pack.addItemModel(Key("spells", "empty")) {
    layerTexture(0, Key("spells", "models/item/empty.png"))
  }
  public val Empty: ItemDefinition = SpellsResourcePack.pack.addItemDefinition(ItemDefinition(EmptyModel.key, BasicItem(EmptyModel.key)))

  public val EmptyItem: ItemStack = buildItemStack(Material.ECHO_SHARD) {
    apply(Empty, this)
    editItemMeta {
      isHideTooltip = true
      displayName(buildText())
    }
  }
}
