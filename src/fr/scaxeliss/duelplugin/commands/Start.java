package fr.scaxeliss.duelplugin.commands;

import fr.scaxeliss.duelplugin.Main;
import fr.scaxeliss.duelplugin.managers.ClearArmor;
import fr.scaxeliss.duelplugin.managers.ItemBuilder;
import fr.scaxeliss.duelplugin.managers.RegisterManager;
import fr.scaxeliss.duelplugin.scoreboard.LobbyScoreboard;
import fr.scaxeliss.duelplugin.timers.GameTimer;
import fr.scaxeliss.duelplugin.timers.StartingTimer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Start implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length != 0){
            sender.sendMessage("§cSyntaxe : /"+cmd.getName());
            return false;
        }


        if(cmd.getName().equalsIgnoreCase("start")) {
            if(!RegisterManager.pvp && !RegisterManager.starting) {
                if(Bukkit.getOnlinePlayers().size() >= 2) {
                    BukkitRunnable startingcountdown = new StartingTimer();
                    startingcountdown.runTaskTimer(Main.getInstance(), 0, 20);
                    RegisterManager.starting = true;
                } else {
                    sender.sendMessage("§cPas assez de joueur !");
                    return false;
                }
            } else {
                sender.sendMessage("§cLe jeu est déjà démarré ! /gstop pour l'arrêter !");
                return false;
            }
        }
        if(cmd.getName().equalsIgnoreCase("gstop")){
            if(RegisterManager.pvp || RegisterManager.starting) {
                Bukkit.broadcastMessage("§cSTTTOOOOPP ! Le jeu a été terminé par " + sender.getName());
                StartingTimer.resetCountdown();
                RegisterManager.pvp = false;
                RegisterManager.starting = false;
                for(Player player: Bukkit.getOnlinePlayers()){
                    player.getInventory().clear();
                    ClearArmor.clearArmor(player);
                    GameTimer.resetTime();

                    player.setHealth(20);
                    player.setFoodLevel(20);

                    Main.kit.replace(player, "Aucun");

                    LobbyScoreboard.Scoreboardd(player, Main.kit.get(player));
                    player.getInventory().setItem(0, ItemBuilder.itemWithLore(Material.COMPASS, "§aKits", "","§eClique pour ouvrir le menu des kits !"));
                }
            } else{
                sender.sendMessage("§cLe jeu n'est pas démarré ! /start pour le commencer !");
                return false;
            }

            Bukkit.getWorld("world").setPVP(false);

        }
        if(cmd.getName().equalsIgnoreCase("fs") && sender.isOp()){
            if(!RegisterManager.starting && !RegisterManager.pvp) {
                BukkitRunnable startingcountdown = new StartingTimer();
                startingcountdown.runTaskTimer(Main.getInstance(), 0, 20);
                RegisterManager.starting = true;
            } else {
                sender.sendMessage("§cLe jeu est déjà démarré ! /gstop pour l'arrêter !");
                return false;
            }
        }
        return false;
    }
}
