package fr.scaxeliss.duelplugin.timers;

import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.scoreboard.GameScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    private static int time = 0;

    @Override
    public void run() {

        if(time != -1) {
            time++;
            for (Player player : Bukkit.getOnlinePlayers()) {
                GameScoreboard.gameScoreboard(player, Kits.kit.get(player));
            }
        } else {
            cancel();
            time = 0;
        }
    }

    public static int getTime(){
        return time;
    }

    public static void resetTime(){
        time = -1;
    }

}
