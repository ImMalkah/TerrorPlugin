package co.terrorsquadmc.terrorplugin;

import co.terrorsquadmc.terrorplugin.Commands.*;
import co.terrorsquadmc.terrorplugin.EventListeners.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        getServer().getPluginManager().registerEvents(new HubProtection(), this);
        Objects.requireNonNull(getCommand("god")).setExecutor(new GodCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
        Objects.requireNonNull(getCommand("sendmessage")).setExecutor(new SendMessageCommand());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(getCommand("book")).setExecutor(new GiveBookCommand());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new Stats());
        Objects.requireNonNull(getCommand("serializeinventory")).setExecutor(new SerializeInventoryUsage());
        Objects.requireNonNull(getCommand("claim")).setExecutor(new Claim());
        Objects.requireNonNull(getCommand("hub")).setExecutor(new Hub());

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
