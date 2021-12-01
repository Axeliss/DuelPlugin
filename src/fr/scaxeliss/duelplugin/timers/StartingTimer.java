package fr.scaxeliss.duelplugin.timers;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.kit.GiveKits;
import fr.scaxeliss.duelplugin.kit.KitSelection;
import fr.scaxeliss.duelplugin.managers.RegisterManager;
import fr.scaxeliss.duelplugin.scoreboard.GameScoreboard;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import fr.scaxeliss.duelplugin.scoreboard.UpdateScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartingTimer extends BukkitRunnable {

    private static int countdown = Main.getInstance().getConfig().getInt("starting-countdown.countdown");

    @Override
    public void run() {

        if(countdown == Main.getInstance().getConfig().getInt("starting-countdown.reset-countdown")){
            cancel();
            countdown = Main.getInstance().getConfig().getInt("starting-countdown.countdown");
            UpdateScoreboard.updateLobbyScoreboard();
            return;
        } else

        if(countdown == 10 ||countdown == 5 ||countdown == 3||countdown == 2 ||countdown == 1) {
            if(RegisterManager.starting) {
                Bukkit.broadcastMessage("§eLancement du jeu dans §7" + getCountdown() + " secondes !");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.CLICK, 10, 2);

                    if(Main.kit.get(player).equalsIgnoreCase("Aucun") && countdown == 10){
                        player.sendMessage("§cVous n'avez pas de kit sélectionné ! Sélectionnez-en un, ou un kit vous sera choisi aléatoirement.");
                    }
                }
            }
        }
        if(countdown == 0) {
            cancel();
            resetCountdown();
            Bukkit.broadcastMessage("§eZééééé parti !");
            BukkitRunnable gametimer = new GameTimer();
            gametimer.runTaskTimer(Main.getInstance(), 20, 20);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Main.kit.get(player).equalsIgnoreCase("aucun")) {
                    KitSelection.giveRandomKit(player);
                }
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 1);
                GameScoreboard.gameScoreboard(player, Main.kit.get(player));
                player.closeInventory();
            }

            RegisterManager.pvp = true;
            RegisterManager.starting = false;

            Bukkit.getWorld("world").setPVP(true);
            resetCountdown();
            GiveKits.giveKits();

        } else {
            UpdateScoreboard.updateLobbyScoreboard();
            countdown--;
        }

    }



    public static int getCountdown(){
        return countdown;
    }

    public static void resetCountdown(){
        if(!RegisterManager.pvp && RegisterManager.starting) {
            countdown = Main.getInstance().getConfig().getInt("starting-countdown.reset-countdown");
        } else {
            countdown = Main.getInstance().getConfig().getInt("starting-countdown.countdown");
            UpdateScoreboard.updateLobbyScoreboard();
        }
    }

}
