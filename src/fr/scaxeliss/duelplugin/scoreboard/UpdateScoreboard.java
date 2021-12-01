package fr.scaxeliss.duelplugin.scoreboard;

import fr.scaxeliss.duelplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UpdateScoreboard {

    public static void updateLobbyScoreboard(){
        for(Player onlinePlayers: Bukkit.getOnlinePlayers()) {
            LobbyScoreboard.Scoreboardd(onlinePlayers, Main.kit.get(onlinePlayers));
        }
    }

    public static void updateGameScoreboard(){
        for(Player onlinePlayers: Bukkit.getOnlinePlayers()) {
            GameScoreboard.gameScoreboard(onlinePlayers, Main.kit.get(onlinePlayers));
        }
    }

}
