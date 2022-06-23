package co.terrorsquadmc.terrorplugin;

import co.terrorsquadmc.terrorplugin.Commands.*;
import co.terrorsquadmc.terrorplugin.EventListeners.*;
import co.terrorsquadmc.terrorplugin.SQL.SQLUtil;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TerrorPlugin extends JavaPlugin implements Listener {

    private static TerrorPlugin plugin;
    private static SQLUtil connection;

    @Override
    public void onEnable()
    {
        System.out.println("Terror enabled. Hello...");
        plugin = this;

        connection = new SQLUtil("mysql.mcprohosting.com", "3306",
                "server_1064047_7cb2d53a",
                "server_1064047",
                "N*Kuj4h0s$&vay3rH#g");
        connection.connect();
        connection.createTable("player_stats", "uuid varchar(36) primary key, kills int, first_login date, blocks_broken long");

        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerKillEvent(), this);
        getCommand("god").setExecutor(new GodCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("sendmessage").setExecutor(new SendMessageCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("book").setExecutor(new GiveBookCommand());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().setIdleTimeout(0); // anti-afk


    }

    @Override
    public void onDisable()
    {
        connection.closeConnection();
    }

    public static TerrorPlugin getPlugin()
    {
        return plugin;
    }

    public static SQLUtil getConnection() {
        return connection;
    }
}
