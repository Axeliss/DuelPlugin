package fr.scaxeliss.duelplugin.managers;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.commands.Kit;
import fr.scaxeliss.duelplugin.commands.Start;
import fr.scaxeliss.duelplugin.commands.Statut;
import fr.scaxeliss.duelplugin.interactions.Interaction;
import fr.scaxeliss.duelplugin.interactions.KitInventoryClick;
import org.bukkit.plugin.PluginManager;

public class RegisterManager {

    private static Main main = Main.getInstance();
    private static PluginManager pm = Main.getInstance().getServer().getPluginManager();
    public static boolean starting = false;
    public static boolean pvp = false;

    public static void Register(){
        main.getCommand("start").setExecutor(new Start());
        main.getCommand("gstop").setExecutor(new Start());
        main.getCommand("fs").setExecutor(new Start());

        main.getCommand("statut").setExecutor(new Statut());

        main.getCommand("kit").setExecutor(new Kit());
        main.getCommand("kitlist").setExecutor(new Kit());
        main.getCommand("kl").setExecutor(new Kit());

        pm.registerEvents(new Interaction(), main);
        pm.registerEvents(new Join(), main);
        pm.registerEvents(new KitInventoryClick(), main);
        pm.registerEvents(new PlayerDeath(), main);
        pm.registerEvents(new Leave(), main);
        pm.registerEvents(new Drop(), main);
        pm.registerEvents(new BreakBlock(), main);
    }

}
