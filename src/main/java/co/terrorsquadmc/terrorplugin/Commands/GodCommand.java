package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings)
    {
        if (commandSender instanceof Player player)
        {
            if (player.hasPermission("terrorplugin.god")) {

                if (strings.length == 0)
                {
                    if (!player.isInvulnerable())
                    {
                        player.setInvulnerable(true);
                        player.sendMessage(new enumOutput().getEnum("command") + "God enabled.");
                    }
                    else
                    {
                        player.setInvulnerable(false);
                        player.sendMessage(new enumOutput().getEnum("command") + "God disabled.");
                    }
                }
                else if (strings.length == 1) {
                    Player target = Bukkit.getPlayer(strings[0]);

                    if (target == null) {
                        player.sendMessage(new enumOutput().getEnum("info") +
                                "That user is currently offline or does not exist");
                    }
                    else {
                        if (!target.isInvulnerable()) {
                            target.setInvulnerable(true);
                            target.sendMessage(new enumOutput().getEnum("info") + player.getName() +
                                    " has enabled god mode for you.");
                            player.sendMessage(new enumOutput().getEnum("info") +
                                    "You have enabled god mode for " + target.getName());
                        }
                        else {
                            target.setInvulnerable(false);
                            target.sendMessage(new enumOutput().getEnum("info") + player.getName() +
                                    " has disabled your god mode.");
                            player.sendMessage(new enumOutput().getEnum("info") +
                                    "You have enabled god mode for " + target.getName());
                        }
                    }
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
