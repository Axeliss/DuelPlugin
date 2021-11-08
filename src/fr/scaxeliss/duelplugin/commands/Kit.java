package fr.scaxeliss.duelplugin.commands;

import fr.scaxeliss.duelplugin.kit.KitSelection;
import fr.scaxeliss.duelplugin.managers.RegisterManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kitlist") || cmd.getName().equalsIgnoreCase("kl")) {
                sender.sendMessage("§f------------- §aKits §f-------------\n§aGuerrier §f: 3 Golden Apples\n                   §fFull fer\n                   §f1 épée en fer\n                  §f Fishing Rod");
                sender.sendMessage("\n§aArcher §f: 5 Golden Apples\n                §fFull fer sauf casque en maille\n§f                Arc Infinité\n");
                sender.sendMessage("\n§aSorcier §f: 5 Golden Apples\n§f                Full or P1\n§f                Epée en pierre T1\n§f                10 potions de heal\n\n+Divers items");
                return false;

        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("kit") && !RegisterManager.pvp) {
                KitSelection.kitInventory(player);
            } else if(RegisterManager.pvp){
                player.sendMessage("§cVous ne pouvez pas prendre de kit à ce stade de la partie.");
                return false;
            }

        } else {
            sender.sendMessage("§cSeul un joueur peut executer cette commande !");
            return false;
        }

        return false;
    }
}
