package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import co.terrorsquadmc.terrorplugin.Utilities.Book;
import co.terrorsquadmc.terrorplugin.Utilities.GsonOperations;
import co.terrorsquadmc.terrorplugin.Utilities.PlayerStats;
import co.terrorsquadmc.terrorplugin.enumOutput;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.Date;

public class PlayerJoinLeaveEvent implements Listener
{
    GsonOperations operations;
    PlayerStats stats;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        operations = new GsonOperations();

        if (player.hasPlayedBefore()) {
            event.joinMessage(Component.text("[" + ChatColor.GREEN + "+" + ChatColor.RESET + "]" + " " + player.getName()));
            Bukkit.getScheduler().scheduleSyncDelayedTask(TerrorPlugin.getPlugin(), () -> player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 5.0F, 1.0F), 50L);

            stats = operations.getFromJson(player.getName(), player.getUniqueId().toString());
            stats.setLastLogin(new Date());
            operations.writeToJsonFile(stats, true);
        }
        else
        {
            Book book = new Book("Malkah", "Getting Started", player);
            book.initializeBook();

            event.joinMessage(Component.text(ChatColor.RED + player.getName() + ChatColor.RESET + " has joined the terrorism for the first time!"));

            stats = new PlayerStats(player.getUniqueId().toString(), player.getName(), 0, 0, 0, new Date(), new Date(), null);
            operations.createJsonFile(player.getName(), player.getUniqueId().toString());
            operations.writeToJsonFile(stats, true);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws IOException {
        Player player = event.getPlayer();
        operations = new GsonOperations();
        stats = operations.getFromJson(player.getName(), player.getUniqueId().toString());

        stats.setLastLogout(new Date());
        operations.writeToJsonFile(stats, true);

        event.quitMessage(Component.text("[" + ChatColor.RED + "-" + ChatColor.RESET + "]" + " " + player.getName()));
    }
}
//displayname - shows both group and name in chat
//username - shows only username in chat
