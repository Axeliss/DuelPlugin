package fr.scaxeliss.duelplugin.managers;

import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import fr.scaxeliss.duelplugin.timers.GameTimer;
import fr.scaxeliss.duelplugin.timers.StartingTimer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity();

        e.setDroppedExp(0);
        e.setKeepInventory(true);
        e.setNewTotalExp(0);

        if(player.getKiller() instanceof Player) {
            Player killer = player.getKiller();

            int random = new Random().nextInt(5);

            switch (random) {
                case 1:
                    e.setDeathMessage("§7" + player.getName() + " §evient de se faire déboiter par §7" + player.getKiller().getName());
                    break;
                case 2:
                    e.setDeathMessage("§7" + player.getName() + " §ea été troué par §7" + player.getKiller().getName());
                    break;
                case 3:
                    e.setDeathMessage("§7" + player.getName() + " §es'est fait humilier par §7" + player.getKiller().getName());
                    break;
                case 4:
                    e.setDeathMessage("§7" + player.getName() + " §en'a pas tellement apprécié combattre §7" + player.getKiller().getName());
                    break;
                case 5:
                    e.setDeathMessage("§7" + player.getName() + " §ea dû se soumettre face à §7" + player.getKiller().getName());
                    break;
                default:
                    e.setDeathMessage("§7" + player.getName() + " §en'a pas tellement apprécié combattre §7" + player.getKiller().getName());
            }

            killer.sendMessage("§6Victoire ! Vous avez gagné votre duel face à §e"+player.getName()+"§6.");
            killer.playSound(killer.getLocation(), Sound.ENDERDRAGON_GROWL, 100, 2);

            player.sendMessage("§cVous avez perdu.. Reessayez, afin de vaincre "+killer.getName()+" !");
        } else {
            e.setDeathMessage("§7"+player.getName()+" §eest mort.");
        }
        StartingTimer.resetCountdown();
        RegisterManager.pvp = false;
        for(Player player1: Bukkit.getOnlinePlayers()) {
            player1.getInventory().clear();
            ClearArmor.clearArmor(player1);
            GameTimer.resetTime();
            Bukkit.getWorld("world").setPVP(false);
            player1.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 100, 1);

            player1.setHealth(20);
            player1.setFoodLevel(20);

            Kits.kit.replace(player1, "Aucun");

            LobbyScoreboard.Scoreboardd(player1, Kits.kit.get(player1));
            player1.getInventory().setItem(0, ItemBuilder.itemWithLore(Material.COMPASS, "§aKits", "", "§eClique pour ouvrir le menu des kits !"));
        }
    }

}
