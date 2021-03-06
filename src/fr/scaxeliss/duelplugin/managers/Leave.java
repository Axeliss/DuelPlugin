package fr.scaxeliss.duelplugin.managers;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import fr.scaxeliss.duelplugin.scoreboard.UpdateScoreboard;
import fr.scaxeliss.duelplugin.timers.GameTimer;
import fr.scaxeliss.duelplugin.timers.StartingTimer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    @EventHandler
    public static void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        int i = Bukkit.getOnlinePlayers().size() - 1;

        e.setQuitMessage(null);

        if(RegisterManager.starting){

            UpdateScoreboard.updateLobbyScoreboard();

            RegisterManager.starting = false;
            StartingTimer.resetCountdown();

            Bukkit.broadcastMessage("§7" + player.getName() + "§e vient de quitter la partie §e(§7" + i + "§e/§72§e)");
            Bukkit.broadcastMessage("§cIl n'y a plus suffisamment de joueur, début annulé !");

            return;
        }

        if (RegisterManager.pvp) {

            Bukkit.broadcastMessage("§7"+player.getName()+"§e vient d'abandonner la partie en quittant §e(§7" + i + "§e/§72§e)");

            StartingTimer.resetCountdown();
            RegisterManager.pvp = false;
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                player1.getInventory().clear();
                ClearArmor.clearArmor(player1);
                GameTimer.resetTime();
                Bukkit.getWorld("world").setPVP(false);

                player1.setHealth(20);
                player1.setFoodLevel(20);

                Main.kit.replace(player1, "Aucun");

                LobbyScoreboard.Scoreboardd(player1, Main.kit.get(player1));
                player1.getInventory().setItem(0, ItemBuilder.itemWithLore(Material.COMPASS, "§aKits", "", "§eClique pour ouvrir le menu des kits !"));
            }

        } else Bukkit.broadcastMessage("§7" + player.getName() + "§e vient de quitter la partie §e(§7" + i + "§e/§72§e)");
    }
}
