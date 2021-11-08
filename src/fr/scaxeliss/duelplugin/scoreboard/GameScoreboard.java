package fr.scaxeliss.duelplugin.scoreboard;

import fr.scaxeliss.duelplugin.timers.GameTimer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class GameScoreboard {

    public static void gameScoreboard(Player player, String kit){

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("§eDUEL", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score space1 = objective.getScore(" ");
        space1.setScore(7);

        Score playername = objective.getScore("Joueur : §a"+player.getName());
        playername.setScore(6);

        Score space2 = objective.getScore("  ");
        space2.setScore(5);

        Score kitS = objective.getScore("Kit : §a"+kit);
        kitS.setScore(4);

        Score space3 = objective.getScore("   ");
        space3.setScore(3);

        Score timer = objective.getScore("Temps écoulé : §a"+ GameTimer.getTime()+"§fs");
        timer.setScore(2);


        Score space4 = objective.getScore("    ");
        space4.setScore(1);

        Score onlinePlayers = objective.getScore("§a=-=-=-=-=-=-=-=-=-=-=-=-=");
        onlinePlayers.setScore(0);

        player.setScoreboard(scoreboard);
    }
}
