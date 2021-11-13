package fr.scaxeliss.duelplugin.managers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    public static ItemStack classicItem(Material material, String name){
        ItemStack it = new ItemStack(material, 1);
        ItemMeta itM = it.getItemMeta();
        itM.setDisplayName(name);
        it.setItemMeta(itM);
        return it;
    }

    public static ItemStack itemWithLore(Material material, String name, String lore, String lore2){
        ItemStack it = classicItem(material, name);
        ItemMeta itm = it.getItemMeta();
        itm.setLore(Arrays.asList(lore, lore2));
        it.setItemMeta(itm);
        return it;
    }

    public static ItemStack itemWithEnchant(Material material, String name, Enchantment enchant, int niveau){
        ItemStack it = classicItem(material, name);
        ItemMeta itm = it.getItemMeta();
        itm.addEnchant(enchant, niveau, true);
        itm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        it.setItemMeta(itm);
        return it;
    }

    public static ItemStack itemWithEnchantAndLore(Material material, String name, String lore, String lore2, Enchantment enchant){
        ItemStack it = new ItemStack(material, 1);
        ItemMeta itM = it.getItemMeta();
        itM.setDisplayName(name);
        itM.setLore(Arrays.asList(lore, lore2));
        itM.addEnchant(enchant, 1, false);
        itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        it.setItemMeta(itM);
        return it;
    }

}
