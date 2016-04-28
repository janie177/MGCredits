package com.minegusta.mgcredits;

import com.minegusta.mgcredits.commands.CreditCommand;
import com.minegusta.mgcredits.files.CreditsUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin{

    public static Plugin PLUGIN;

    @Override
    public void onEnable()
    {
        //Plugin
        PLUGIN = this;

        //Files
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        //Commands
        getCommand("addcredits").setExecutor(new CreditCommand());
        getCommand("credits").setExecutor(new CreditCommand());
        getCommand("removecredits").setExecutor(new CreditCommand());


        Logger logger = Bukkit.getLogger();
        if(!CreditsUtil.init())
        {
            logger.info(ChatColor. RED + " - - - - - - - - - - - - ");
            logger.info("[MGCredits] Disabling because no database connection could be made.");
            logger.info(ChatColor. RED + " - - - - - - - - - - - - ");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
}
