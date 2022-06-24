package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.SQL.PlayerStats;
import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;


public class PlayerBlockBreakEvent implements Listener {

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) throws SQLException {
        Player player = event.getPlayer();

        if (!TerrorPlugin.getConnection().isConnected())
        {
            TerrorPlugin.getConnection().connect();
            updatePlayerBlocksBroken(player);
        } else {
            updatePlayerBlocksBroken(player);
        }
    }

    private void updatePlayerBlocksBroken(Player player) throws SQLException {
        PlayerStats stats = TerrorPlugin.getConnection().findPlayerByUUID(player.getUniqueId().toString());

        stats.setBlocksBroken(stats.getBlocksBroken() + 1);
        TerrorPlugin.getConnection().updateStats(stats);
    }
}

