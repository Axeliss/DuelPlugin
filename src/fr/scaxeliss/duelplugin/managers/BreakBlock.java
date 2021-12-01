package fr.scaxeliss.duelplugin.managers;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {

    @EventHandler
    public static void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block bloc = e.getBlock();

        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            if (bloc.getType() != Material.WOOD) {
                player.sendMessage("§cErreur : vous pouvez casser que des blocs posés par des joueurs.");
                e.setCancelled(true);
            }
        }
    }

}
