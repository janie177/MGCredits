package com.minegusta.mgcredits.files;

import org.bukkit.entity.Player;

public class CreditsManager
{

    private static Config conf = new Config();
    /**
     * Get the credits for the given player.
     * @param p The player.
     * @return The amount of credits on file.
     */
    public static int getCredits(Player p)
    {
        return Config.getCredits(p.getUniqueId());
    }

    /**
     * Remove credits from a player.
     * @param p The player to remove it from.
     * @param a The amount to remove.
     */
    public static boolean removeCredits(Player p, int a)
    {
        return Config.removeCredits(p.getUniqueId(), a);
    }

    /**
     * Add credits to a certain player.
     * @param p The player to add credits to.
     * @param a The amount of credits to add.
     */
    public static void addCredits(Player p, int a)
    {
        Config.addCredits(p.getUniqueId(), a);
    }
}
