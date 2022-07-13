package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.Utilities.GsonOperations;
import co.terrorsquadmc.terrorplugin.Utilities.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.IOException;

public class PlayerKillEvent implements Listener {
    GsonOperations operations;

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) throws IOException {
        Player killer = event.getEntity().getKiller();

        if (killer != null) {
            operations = new GsonOperations();
            PlayerStats stats = operations.getFromJson(killer.getName(), killer.getUniqueId().toString());

            stats.setKills(stats.getKills() + 1);
            operations.writeToJsonFile(stats, true);
        }
    }
}

