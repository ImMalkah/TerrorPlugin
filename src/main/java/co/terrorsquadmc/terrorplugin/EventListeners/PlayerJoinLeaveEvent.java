package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.SQL.PlayerStats;
import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import co.terrorsquadmc.terrorplugin.Utilities.Book;
import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.Date;

public class PlayerJoinLeaveEvent implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();

        if (player.hasPlayedBefore())
        {
            event.setJoinMessage(new enumOutput().getEnum("info") + ChatColor.RED + player.getDisplayName() + ChatColor.RESET +
                    " has joined the dark side");
            Bukkit.getScheduler().scheduleSyncDelayedTask(TerrorPlugin.getPlugin(), () -> player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 5.0F, 1.0F), 50L);

            PlayerStats stats = TerrorPlugin.getConnection().findPlayerByUUID(player.getUniqueId().toString());

            if (stats == null) {
                stats = new PlayerStats(player.getUniqueId().toString(), 0, new Date(), 0, 0, player.getName());
                TerrorPlugin.getConnection().createStats(stats);
                player.sendMessage("No stats found for existing user. Creating stats table!");
            } else player.sendMessage("Stats found! Loading table...");
        }
        else
        {
            PlayerStats stats = new PlayerStats(player.getUniqueId().toString(), 0, new Date(), 0, 0, player.getName());
            TerrorPlugin.getConnection().createStats(stats);
            player.sendMessage("Creating new user stats table...");

            Book book = new Book("Malkah", "Getting Started", player);
            book.initializeBook();

            event.setJoinMessage(new enumOutput().getEnum("info") + ChatColor.RED + player.getDisplayName() + ChatColor.RESET +
                    " has joined the terrorism for the first time. Allahu Akbar!");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        event.setQuitMessage(new enumOutput().getEnum("info") + ChatColor.RED + player.getDisplayName() + ChatColor.RESET +
                " has left the dark side");
    }
}

//displayname - shows both group and name in chat
//username - shows only username in chat