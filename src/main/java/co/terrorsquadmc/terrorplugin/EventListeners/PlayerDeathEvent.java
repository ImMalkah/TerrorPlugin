package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.SQL.PlayerStats;
import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.sql.SQLException;

public class PlayerDeathEvent implements Listener
{

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event) throws SQLException {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        EntityDamageEvent entityDamageEvent = player.getLastDamageCause();
        Location playerLocation = player.getLocation();

        if (entityDamageEvent instanceof EntityDamageByEntityEvent entityEvent)
        {
            Entity entity = entityEvent.getDamager();
            if (entity.getType() == EntityType.ARROW)
            {
                event.setDeathMessage(new enumOutput().getEnum("info") + player.getDisplayName() + " died by Skeleton " +
                        "X: " + playerLocation.getBlockX() + " " +
                        "Y: " + playerLocation.getBlockY() + " " +
                        "Z: " + playerLocation.getBlockZ());

                setSqlDeaths(player);
            }
            else if (entity.getType() == EntityType.PLAYER) {
                event.setDeathMessage(player.getName() + " was killed by " + killer.getDisplayName());
                if (!TerrorPlugin.getConnection().isConnected())
                {
                    TerrorPlugin.getConnection().connect();
                    setSqlDeaths(player);
                } else {
                    setSqlDeaths(player);
                }
            }
            else {
                event.setDeathMessage(new enumOutput().getEnum("info") + player.getDisplayName() + " died by " + entity.getName() + " " +
                        "X: " + playerLocation.getBlockX() + " " +
                        "Y: " + playerLocation.getBlockY() + " " +
                        "Z: " + playerLocation.getBlockZ());

                if (!TerrorPlugin.getConnection().isConnected())
                {
                    TerrorPlugin.getConnection().connect();
                    setSqlDeaths(player);
                } else {
                    setSqlDeaths(player);
                }
            }
        }
    }

    private void setSqlDeaths(Player player) throws SQLException {
        PlayerStats stats = TerrorPlugin.getConnection().findPlayerByUUID(player.getUniqueId().toString());
        stats.setDeaths(stats.getDeaths() + 1);
        TerrorPlugin.getConnection().updateStats(stats);
    }
}
