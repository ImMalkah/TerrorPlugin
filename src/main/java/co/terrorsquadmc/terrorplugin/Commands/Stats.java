package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.Utilities.GsonOperations;
import co.terrorsquadmc.terrorplugin.Utilities.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            GsonOperations operations = new GsonOperations();
            SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy");
            String firstLoginDate, lastLoginDate;

            try {
                PlayerStats stats = operations.getFromJson(player.getName(), player.getUniqueId().toString());
                firstLoginDate = DateFor.format(stats.getFirstLogin());
                lastLoginDate = DateFor.format(stats.getLastLogin());
                player.sendMessage(ChatColor.GOLD + "Kills: " + ChatColor.RESET + stats.getKills()
                        + ChatColor.GOLD + " -- Deaths: " + ChatColor.RESET + stats.getDeaths()
                        + ChatColor.GOLD + " -- Join Date: " + ChatColor.RESET + firstLoginDate
                        + ChatColor.GOLD + " -- Last Login: " + ChatColor.RESET + lastLoginDate
                        + ChatColor.GOLD + " -- Blocks Broken: " + ChatColor.RESET + stats.getBlocksBroken());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found!");
            }
        }
        return true;
    }
}
