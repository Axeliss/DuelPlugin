package fr.scaxeliss.duelplugin.kit;

import fr.scaxeliss.duelplugin.managers.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveKits {

    private static void giveKitArcher(Player player){
        player.getInventory().clear();
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        player.getInventory().setItem(0, ItemBuilder.itemWithEnchant(Material.BOW, "§fArc Infini", Enchantment.ARROW_INFINITE));
        player.getInventory().setItem(6, new ItemStack(Material.ARROW));
        player.getInventory().setItem(1, new ItemStack(Material.WOOD, 64));
        player.getInventory().setItem(2, new ItemStack(Material.WOOD, 64));
        player.getInventory().setItem(2, new ItemStack(Material.GOLDEN_APPLE, 5));
        player.getInventory().setItem(8, ItemBuilder.itemWithEnchant(Material.IRON_AXE, "§fHache du Bûcheron Dimitri",Enchantment.DIG_SPEED ));
        player.getInventory().setHeldItemSlot(0);
    }

    private static void giveKitWitch(Player player) {
        player.getInventory().clear();
        player.getInventory().setChestplate(ItemBuilder.itemWithEnchant(Material.GOLD_CHESTPLATE, "§fPlastron en or P1", Enchantment.PROTECTION_ENVIRONMENTAL));
        player.getInventory().setHelmet(ItemBuilder.itemWithEnchant(Material.GOLD_HELMET, "§fCasque en or P1", Enchantment.PROTECTION_ENVIRONMENTAL));
        player.getInventory().setLeggings(ItemBuilder.itemWithEnchant(Material.GOLD_LEGGINGS, "§fJambières en or P1", Enchantment.PROTECTION_ENVIRONMENTAL));
        player.getInventory().setBoots(ItemBuilder.itemWithEnchant(Material.GOLD_BOOTS, "§fBottes en or P1", Enchantment.PROTECTION_ENVIRONMENTAL));
        player.getInventory().setItem(1, new ItemStack(Material.WOOD, 64));
        player.getInventory().setItem(2, new ItemStack(Material.WOOD, 64));
        player.getInventory().setItem(3, new ItemStack(Material.GOLDEN_APPLE, 5));
        player.getInventory().setItem(0, ItemBuilder.itemWithEnchant(Material.STONE_SWORD,"§fEpée en pierre" ,Enchantment.DAMAGE_ALL));
        player.getInventory().setItem(4, new ItemStack(Material.POTION, 10, (byte) 8229));
        player.getInventory().setItem(8, ItemBuilder.itemWithEnchant(Material.IRON_AXE, "§fHache du Bucheron Dimitri",Enchantment.DIG_SPEED));
        player.getInventory().setHeldItemSlot(0);
    }

    private static void giveKitWarrior(Player player) {
        player.getInventory().clear();
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        player.getInventory().setItem(6, new ItemStack(Material.WOOD, 64));
        player.getInventory().setItem(5, new ItemStack(Material.WOOD, 64));
        player.getInventory().setItem(2, new ItemStack(Material.GOLDEN_APPLE, 3));
        player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD ));
        player.getInventory().setItem(1, new ItemStack(Material.FISHING_ROD));
        player.getInventory().setItem(8, ItemBuilder.itemWithEnchant(Material.IRON_AXE, "§fHache du Bûcheron Dimitri",Enchantment.DIG_SPEED ));

    }


    public static void giveKits(){
        for(Player player: Bukkit.getOnlinePlayers()){
            if(Kits.kit.get(player).equalsIgnoreCase("Archer")){
                giveKitArcher(player);
            } else if(Kits.kit.get(player).equalsIgnoreCase("Sorcier")){
                giveKitWitch(player);
            } else if(Kits.kit.get(player).equalsIgnoreCase("Guerrier")){
                giveKitWarrior(player);
            }
        }
    }

}
