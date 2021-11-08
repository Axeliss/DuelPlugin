package fr.scaxeliss.duelplugin.timers;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.kit.GiveKits;
import fr.scaxeliss.duelplugin.kit.KitSelection;
import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.managers.RegisterManager;
import fr.scaxeliss.duelplugin.scoreboard.GameScoreboard;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartingTimer extends BukkitRunnable {

    private static int countdown = Main.getInstance().getConfig().getInt("starting-countdown.countdown");

    @Override
    public void run() {

        if(countdown == 10 && RegisterManager.starting){
            for(Player player: Bukkit.getOnlinePlayers()){
                if(Kits.kit.get(player).equalsIgnoreCase("Aucun")){
                    player.sendMessage("§cVous n'avez pas de kit sélectionné ! Sélectionnez-en un, ou un kit vous sera choisi aléatoirement.");
                }
            }
        }
        if(countdown == 10 ||countdown == 5 ||countdown == 3||countdown == 2 ||countdown == 1) {
            if(RegisterManager.starting) {
                Bukkit.broadcastMessage("§eLancement du jeu dans §7" + getCountdown() + " secondes !");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.CLICK, 10, 2);
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
                if (Kits.kit.get(player).equalsIgnoreCase("aucun")) {
                    KitSelection.giveRandomKit(player);
                }
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 1);
                GameScoreboard.gameScoreboard(player, Kits.kit.get(player));
                player.closeInventory();
            }
            RegisterManager.pvp = true;
            RegisterManager.starting = false;

            Bukkit.getWorld("world").setPVP(true);
            resetCountdown();
            GiveKits.giveKits();

        } else {
            for(Player player: Bukkit.getOnlinePlayers()){
                LobbyScoreboard.Scoreboardd(player, Kits.kit.get(player));
            }
            countdown--;
        }

    }



    public static int getCountdown(){
        return countdown;
    }

    public static void resetCountdown(){
        countdown = Main.getInstance().getConfig().getInt("starting-countdown.countdown");
    }

}
