package fr.scaxeliss.duelplugin.interactions;

import fr.scaxeliss.duelplugin.kit.KitSelection;
import fr.scaxeliss.duelplugin.managers.ItemBuilder;
import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitInventoryClick implements Listener {

    @EventHandler
    public static void onKitInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack current = e.getCurrentItem();

        if(current != null){

         if(current.getType() == Material.COMPASS && current.hasItemMeta() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aKits")){
             KitSelection.kitInventory(player);
             e.setCancelled(true);
             player.getInventory().setItem(0, ItemBuilder.itemWithLore(Material.COMPASS, "§aKits", "", "§eClique pour ouvrir le menu des kits !"));
         }

         if(inv.getName().equalsIgnoreCase("§7Kits")){
             e.setCancelled(true);


             if(current.getType() == Material.BOW && current.hasItemMeta() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aArcher") && !Kits.kit.get(player).equalsIgnoreCase("archer")){
                 Kits.kit.put(player, "Archer");
                 player.sendMessage("§eKit sélectionné : "+Kits.kit.get(player.getPlayer()));
                 player.playSound(player.getLocation(), Sound.CLICK, 30, 2);

                 KitSelection.kitInventory(player);
                 LobbyScoreboard.Scoreboardd(player, "§a"+Kits.kit.get(player.getPlayer()));
                 return;
             } else
             if(current.getType() == Material.BOW && current.hasItemMeta() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aArcher") && Kits.kit.get(player).equalsIgnoreCase("archer")){
                 player.sendMessage("§cKit désélectionné : Archer");
                 player.playSound(player.getLocation(), Sound.CLICK, 30, 2);
                 Kits.kit.replace(player, "Aucun");
                 KitSelection.kitInventory(player);
                 LobbyScoreboard.Scoreboardd(player, "§aAucun");
                 return;
             }


             if(current.getType() == Material.IRON_SWORD && current.hasItemMeta() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aGuerrier") && !Kits.kit.get(player).equalsIgnoreCase("guerrier")){
                 Kits.kit.put(player, "Guerrier");
                 player.sendMessage("§eKit sélectionné : "+Kits.kit.get(player.getPlayer()));
                 player.playSound(player.getLocation(), Sound.CLICK, 30, 2);

                 KitSelection.kitInventory(player);
                 LobbyScoreboard.Scoreboardd(player, "§a"+Kits.kit.get(player.getPlayer()));
                 return;
             } else
             if(current.getType() == Material.IRON_SWORD && current.hasItemMeta() && current.getItemMeta().getDisplayName().startsWith("§aGuerrier") && Kits.kit.get(player).equalsIgnoreCase("guerrier")){
                 player.sendMessage("§cKit désélectionné : Guerrier");
                 player.playSound(player.getLocation(), Sound.CLICK, 30, 2);
                 Kits.kit.replace(player, "Aucun");
                 KitSelection.kitInventory(player);
                 LobbyScoreboard.Scoreboardd(player, "§aAucun");
                 return;
             }


             if(current.getType() == Material.POTION && current.hasItemMeta() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aSorcier") && !Kits.kit.get(player).equalsIgnoreCase("sorcier")){
                 Kits.kit.put(player, "Sorcier");
                 player.sendMessage("§eKit sélectionné : "+Kits.kit.get(player.getPlayer()));
                 player.playSound(player.getLocation(), Sound.CLICK, 30, 2);

                 KitSelection.kitInventory(player);
                 LobbyScoreboard.Scoreboardd(player, "Sorcier");
                 return;
             } else
             if(current.getType() == Material.POTION && current.hasItemMeta() && current.getItemMeta().getDisplayName().startsWith("§aSorcier") && Kits.kit.get(player).equalsIgnoreCase("sorcier")){
                 player.sendMessage("§cKit désélectionné : Sorcier");
                 player.playSound(player.getLocation(), Sound.CLICK, 30, 2);
                 Kits.kit.replace(player, "Aucun");
                 KitSelection.kitInventory(player.getPlayer());
                 LobbyScoreboard.Scoreboardd(player, "§aAucun");
                 return;
             }

             if(current.getType() == Material.ENDER_CHEST && current.hasItemMeta() && current.getItemMeta().getDisplayName().startsWith("§aAléatoire")) {
                 KitSelection.giveRandomKit(player);
             }


             if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().getDisplayName().startsWith("§cFermer")){
                 player.closeInventory();
             }

         }

        }
    }

}
