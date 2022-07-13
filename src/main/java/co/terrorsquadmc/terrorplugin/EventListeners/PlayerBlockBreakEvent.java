package co.terrorsquadmc.terrorplugin.EventListeners;

import co.terrorsquadmc.terrorplugin.Utilities.GsonOperations;
import co.terrorsquadmc.terrorplugin.Utilities.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;

public class PlayerBlockBreakEvent implements Listener {
    GsonOperations operations;

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) throws IOException {
        Player player = event.getPlayer();

        operations = new GsonOperations();
        PlayerStats stats = operations.getFromJson(player.getName(), player.getUniqueId().toString());

        stats.setBlocksBroken(stats.getBlocksBroken() + 1);
        operations.writeToJsonFile(stats, true);
    }
}

