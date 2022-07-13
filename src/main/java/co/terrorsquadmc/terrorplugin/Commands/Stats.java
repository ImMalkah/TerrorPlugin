package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.Utilities.GsonOperations;
import co.terrorsquadmc.terrorplugin.Utilities.PlayerStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            GsonOperations operations = new GsonOperations();
            SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy");
            String firstLoginDate, lastLoginDate;

            try {
                PlayerStats stats = operations.getFromJson(player.getName(), player.getUniqueId().toString());
                firstLoginDate = DateFor.format(stats.getFirstLogin());
                lastLoginDate = DateFor.format(stats.getLastLogin());
                player.sendMessage("Kills: " + stats.getKills() + " -- Deaths: " + stats.getDeaths() + " -- Join Date: " + firstLoginDate + " -- Last Login: " + lastLoginDate);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found!");
            }
        }
        return true;
    }
}
