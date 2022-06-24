package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.SQL.PlayerStats;
import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.sql.SQLException;

public class PlayerKillEvent implements Listener {

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) throws SQLException {
        Player killer = event.getEntity().getKiller();

        if (killer != null) {
            if (!TerrorPlugin.getConnection().isConnected()) {
                TerrorPlugin.getConnection().connect();
                updatePlayerKillsStats(killer);
            } else {
                updatePlayerKillsStats(killer);
            }
        }
    }

    private void updatePlayerKillsStats(Player killer) throws SQLException {
        PlayerStats stats = TerrorPlugin.getConnection().findPlayerByUUID(killer.getUniqueId().toString());
        stats.setKills(stats.getKills() + 1);
        TerrorPlugin.getConnection().updateStats(stats);
    }
}

