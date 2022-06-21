package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (commandSender instanceof Player player)
        {
            if (player.hasPermission("terrorplugin.feed"))
            {
                if (strings.length == 0)
                {
                    player.setFoodLevel(20);
                    player.setSaturation(10.0F);
                    player.sendMessage(new enumOutput().getEnum("command") + "Hunger & saturation refilled.");
                }
                else if (strings.length == 1)
                {
                    Player target = Bukkit.getPlayer(strings[0]);
                    if ((target != null) && target.isOnline())
                    {
                        target.setFoodLevel(20);
                        target.setSaturation(10.0F);
                        target.sendMessage(new enumOutput().getEnum("command") + player.getName() + " has refilled your hunger and saturation.");
                        player.sendMessage(new enumOutput().getEnum("command") + "You have refilled " + target.getName() + "'s hunger and saturation.");
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
