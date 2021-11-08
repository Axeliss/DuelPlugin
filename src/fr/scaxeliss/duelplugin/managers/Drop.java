package fr.scaxeliss.duelplugin.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop implements Listener {

    @EventHandler
    public static void onDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();

        e.setCancelled(true);
    }
}
