package fr.scaxeliss.duelplugin.kit;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.managers.ItemBuilder;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class KitSelection {

    public static void kitInventory(Player player){
        Inventory kit = Bukkit.createInventory(null, 54, "§7Kits");

        if(!Main.kit.get(player).equalsIgnoreCase("archer")){
            kit.setItem(0,ItemBuilder.itemWithLore(Material.BOW, "§aArcher", "", "§eClique pour sélectionner le kit Archer !"));
        } else if(Main.kit.get(player).equalsIgnoreCase("Archer")) {
            kit.setItem(0,ItemBuilder.itemWithEnchantAndLore(Material.BOW, "§aArcher", "","§eSELECTIONNE", Enchantment.DAMAGE_ALL));
        }

        if(!Main.kit.get(player).equalsIgnoreCase("guerrier")){
            kit.setItem(1,ItemBuilder.itemWithLore(Material.IRON_SWORD, "§aGuerrier", "", "§eClique pour sélectionner le kit Guerrier !"));
        } else if(Main.kit.get(player).equalsIgnoreCase("Guerrier")){
            kit.setItem(1,ItemBuilder.itemWithEnchantAndLore(Material.IRON_SWORD, "§aGuerrier", "","§eSELECTIONNE", Enchantment.DAMAGE_ALL));
        }

        if(!Main.kit.get(player).equalsIgnoreCase("sorcier")){
            kit.setItem(2,ItemBuilder.itemWithLore(Material.POTION, "§aSorcier", "", "§eClique pour sélectionner le kit Sorcier !"));
        } else if(Main.kit.get(player).equalsIgnoreCase("sorcier")){
            kit.setItem(2,ItemBuilder.itemWithEnchantAndLore(Material.POTION, "§aSorcier", "","§eSELECTIONNE", Enchantment.DAMAGE_ALL));
        }

        if(Main.kit.get(player).equalsIgnoreCase("aucun")){

        }

        kit.setItem(53,ItemBuilder.classicItem(Material.BARRIER, "§cFermer"));
        kit.setItem(49,ItemBuilder.classicItem(Material.ENDER_CHEST, "§aAléatoire"));

        player.openInventory(kit);
        player.getInventory().setItem(0, ItemBuilder.itemWithLore(Material.COMPASS, "§aKits", "","§eClique pour ouvrir le menu des kits !"));
    }

    public static void giveRandomKit(Player player){
        int random = new Random().nextInt(3);

        if(random == 0){
            Main.kit.put(player, "Sorcier");
            player.sendMessage("§eKit aléatoire sélectionné : "+Main.kit.get(player));
            player.playSound(player.getLocation(), Sound.CLICK, 30, 2);

            KitSelection.kitInventory(player.getPlayer());
            LobbyScoreboard.Scoreboardd(player, "§aSorcier");
        }
        else if(random == 1){
            Main.kit.put(player, "Guerrier");
            player.sendMessage("§eKit aléatoire sélectionné : "+Main.kit.get(player));
            player.playSound(player.getLocation(), Sound.CLICK, 30, 2);

            KitSelection.kitInventory(player);
            LobbyScoreboard.Scoreboardd(player, "§aGuerrier");
        }
        else if(random == 2){
            Main.kit.put(player, "Archer");
            player.sendMessage("§eKit aléatoire sélectionné : "+Main.kit.get(player));
            player.playSound(player.getLocation(), Sound.CLICK, 30, 2);

            KitSelection.kitInventory(player);
            LobbyScoreboard.Scoreboardd(player, "§aArcher");
        }

    }
}
