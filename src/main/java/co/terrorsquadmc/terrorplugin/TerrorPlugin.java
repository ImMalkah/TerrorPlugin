package co.terrorsquadmc.terrorplugin;

import co.terrorsquadmc.terrorplugin.Commands.*;
import co.terrorsquadmc.terrorplugin.EventListeners.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TerrorPlugin extends JavaPlugin implements Listener {

    private static TerrorPlugin plugin;

    @Override
    public void onEnable()
    {
        System.out.println("Terror enabled. Hello!");
        plugin = this;

        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerKillEvent(), this);
        getCommand("god").setExecutor(new GodCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("sendmessage").setExecutor(new SendMessageCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("book").setExecutor(new GiveBookCommand());
        getCommand("stats").setExecutor(new Stats());
        getCommand("serializeinventory").setExecutor(new SerializeInventoryUsage());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().setIdleTimeout(0); // anti-afk
    }

    @Override
    public void onDisable()
    {

    }

    public static TerrorPlugin getPlugin()
    {
        return plugin;
    }


}
