package fr.scaxeliss.duelplugin;

import fr.scaxeliss.duelplugin.managers.RegisterManager;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.WeakHashMap;

public class Main extends JavaPlugin {

    private static Main instance;
    public static WeakHashMap<Player, String> kit = new WeakHashMap<>();

    @Override
    public void onLoad(){
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        instance = this;
        RegisterManager.Register();
        Bukkit.getConsoleSender().sendMessage("§aDuelPlugin fonctionnel !");

        for(Player onlinePlayers: Bukkit.getOnlinePlayers()){
            kit.put(onlinePlayers, "Aucun");
            LobbyScoreboard.Scoreboardd(onlinePlayers, kit.get(onlinePlayers));
        }

    }

    public static Main getInstance(){
        return instance;
    }

}
