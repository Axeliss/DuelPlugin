package fr.scaxeliss.duelplugin.timers;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.scoreboard.GameScoreboard;
import fr.scaxeliss.duelplugin.scoreboard.UpdateScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    private static int time = 0;

    @Override
    public void run() {

        if(time != -1) {
            time++;
            UpdateScoreboard.updateGameScoreboard();
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
