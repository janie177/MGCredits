package com.minegusta.mgcredits.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreditCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args)
    {

        //Check how many credits you have.
        if(cmd.getName().equalsIgnoreCase("credits") && s instanceof Player)
        {
            Player p = (Player) s;
            p.sendMessage(ChatColor.GOLD + "[MG] " + ChatColor.YELLOW + "You have " + ChatColor.LIGHT_PURPLE + Config.getCredits(p.getUniqueId()) + ChatColor.YELLOW + " credits.");
            return true;
        }

        //Command to give credits from console.
        else if(cmd.getName().equalsIgnoreCase("addcredits") && s.isOp() && args.length > 1)
        {
            try
            {
                Player p = Bukkit.getOfflinePlayer(args[0]).getPlayer();
                int amount = Integer.parseInt(args[1]);
                Config.addCredits(p.getUniqueId(), amount);

            } catch (Exception ignored)
            {

            }
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("removecredits") && s.isOp() && args.length > 1)
        {
            try
            {
                Player p = Bukkit.getOfflinePlayer(args[0]).getPlayer();
                int amount = Integer.parseInt(args[1]);
                Config.removeCredits(p.getUniqueId(), amount);

            } catch (Exception ignored)
            {

            }
        }
        return true;
    }
}
