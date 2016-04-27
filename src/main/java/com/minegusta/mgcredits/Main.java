package com.minegusta.mgcredits;

import com.minegusta.mgcredits.commands.CreditCommand;
import com.minegusta.mgcredits.files.CreditsUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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

        CreditsUtil.init();

        //Commands
        getCommand("addcredits").setExecutor(new CreditCommand());
        getCommand("credits").setExecutor(new CreditCommand());
        getCommand("removecredits").setExecutor(new CreditCommand());
    }
}
