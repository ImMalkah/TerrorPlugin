package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.Utilities.GsonOperations;
import co.terrorsquadmc.terrorplugin.Utilities.PlayerStats;
import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.IOException;

public class PlayerDeathEvent implements Listener
{
    GsonOperations operations;

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event) throws IOException {
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

                updateStatsDeaths(player);
            }
            else if (entity.getType() == EntityType.PLAYER) {
                event.setDeathMessage(player.getName() + " was killed by " + killer.getName());

                updateStatsDeaths(player);
            }
            else {
                event.setDeathMessage(new enumOutput().getEnum("info") + player.getName() + " died by " + entity.getName() + " " +
                        "X: " + playerLocation.getBlockX() + " " +
                        "Y: " + playerLocation.getBlockY() + " " +
                        "Z: " + playerLocation.getBlockZ());

                updateStatsDeaths(player);
            }
        }
    }

    private void updateStatsDeaths(Player player) throws IOException {
        operations = new GsonOperations();
        PlayerStats stats = operations.getFromJson(player.getName(), player.getUniqueId().toString());

        stats.setDeaths(stats.getDeaths() + 1);
        operations.writeToJsonFile(stats, true);
    }
}
