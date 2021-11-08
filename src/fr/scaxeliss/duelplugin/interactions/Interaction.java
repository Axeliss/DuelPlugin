package fr.scaxeliss.duelplugin.interactions;

import fr.scaxeliss.duelplugin.kit.KitSelection;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Interaction implements Listener {

    @EventHandler
    public static void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack current = e.getItem();

        if (current != null) {

            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

                if (current.getType() == Material.COMPASS && current.hasItemMeta() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aKits")) {
                    KitSelection.kitInventory(player);
                }
            }
        }

    }
}
