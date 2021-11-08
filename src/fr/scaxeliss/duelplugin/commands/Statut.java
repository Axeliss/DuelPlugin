package fr.scaxeliss.duelplugin.commands;

import fr.scaxeliss.duelplugin.kit.Kits;
import fr.scaxeliss.duelplugin.managers.RegisterManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Statut implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(RegisterManager.starting){
            sender.sendMessage("§6Statut : §aCommencé");
            return false;
        } else {
            sender.sendMessage("§6Statut : §cArrêté");
            sender.sendMessage("votre kit : "+ Kits.kit.get(sender));
            return false;
        }
    }
}
