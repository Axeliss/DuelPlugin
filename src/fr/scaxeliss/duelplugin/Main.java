package fr.scaxeliss.duelplugin;

import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.managers.RegisterManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

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
            Kits.kit.put(onlinePlayers, "Aucun");
        }

    }

    public static Main getInstance(){
        return instance;
    }

}
