package fr.scaxeliss.duelplugin.managers;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import fr.scaxeliss.duelplugin.timers.StartingTimer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Join implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.getInventory().clear();
        ClearArmor.clearArmor(player);

        int x = (int) Main.getInstance().getConfig().get("spawnpoint.x");
        int y = (int) Main.getInstance().getConfig().get("spawnpoint.y");
        int z = (int) Main.getInstance().getConfig().get("spawnpoint.z");


        Location spawnpoint = new Location(player.getWorld(), x, y, z, 0.9f, 3.1f);

        player.teleport(spawnpoint);

        Bukkit.getWorld("world").setPVP(false);
        Bukkit.getWorld("world").setGameRuleValue("showDeathMessages", "true");
        Bukkit.getWorld("world").setGameRuleValue("doDaylightCycle", "false");
        Bukkit.getWorld("world").setDifficulty(Difficulty.NORMAL);
        Bukkit.getWorld("world").setMonsterSpawnLimit(0);

        if(RegisterManager.pvp || Bukkit.getOnlinePlayers().size() > 2){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§7La partie est complete. Vous pouvez regarder le duel actuel.");
            e.setJoinMessage("§7"+player.getName()+"§7 vient de rejoindre la file d'attente.");
            return;
        } else
        player.setGameMode(GameMode.SURVIVAL);

        e.setJoinMessage("§7"+player.getName()+"§e vient de rejoindre la partie  §e(§7"+ Bukkit.getServer().getOnlinePlayers().size()+"§e/§72§e)");
        if(!RegisterManager.pvp && !RegisterManager.starting && Bukkit.getOnlinePlayers().size() == 2){
            BukkitRunnable startingcountdown = new StartingTimer();
            startingcountdown.runTaskTimer(Main.getInstance(), 0, 20);
            RegisterManager.starting = true;
            Bukkit.broadcastMessage("§e La partie est désormais complète ! Lancement du jeu dans §7"+StartingTimer.getCountdown()+" secondes !");
        }
        player.getInventory().setItem(0, ItemBuilder.itemWithLore(Material.COMPASS, "§aKits", "","§eClique pour ouvrir le menu des kits !"));
        Kits.kit.put(player, "Aucun");

        LobbyScoreboard.Scoreboardd(player, "§a"+ Kits.kit.get(player));
    }
}
