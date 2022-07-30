package co.terrorsquadmc.terrorplugin.EventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class HubProtection implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!event.getPlayer().isOp())
            event.setCancelled(true);
    }
}
