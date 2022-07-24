package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)
    {
        if (sender instanceof Player player)
        {
            if (player.hasPermission("terrorplugin.fly"))
            {
                if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null)
                    {
                        player.sendMessage(new enumOutput().getEnum("info") +
                                args[0] + " doesn't exist or is currently offline.");
                    } else
                    {
                        if (target.getAllowFlight())
                        {
                            target.setAllowFlight(false);
                            target.sendMessage(new enumOutput().getEnum("info") +
                                    player.getName() + " has disabled your ability to fly!");
                            player.sendMessage(new enumOutput().getEnum("info") +
                                    "You have set " + target.getName() + "'s flight mode off");
                        } else if (!target.getAllowFlight())
                        {
                            target.setAllowFlight(true);
                            target.sendMessage(new enumOutput().getEnum("info") +
                                    player.getName() + " has given you the ability to fly!");
                            player.sendMessage(new enumOutput().getEnum("info") +
                                    "You have set " + target.getName() + "'s flight mode on");
                        }
                    }
                } else if (args.length == 0)
                {
                    if (player.getAllowFlight())
                    {
                        player.setAllowFlight(false);
                        player.sendMessage(new enumOutput().getEnum("info") + "Flight mode disabled.");
                    } else if (!player.getAllowFlight())
                    {
                        player.setAllowFlight(true);
                        player.sendMessage(new enumOutput().getEnum("info") + "Flight mode enabled.");
                    }
                } else
                {
                    return false;
                }
            }
            else
            {
                player.sendMessage(new enumOutput().getEnum("error") + "You do not have the required permissions to use this command.");
            }
        }
        return true;
    }
}
