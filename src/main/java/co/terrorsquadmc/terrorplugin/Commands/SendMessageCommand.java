package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.enumOutput;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendMessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (commandSender instanceof Player player)
        {
            if (player.hasPermission("terrorplugin.msg"))
            {
                if (args.length == 0)
                {
                    player.sendMessage("You must send a message to a specific player.");
                }
                else if (args.length == 1)
                {
                    player.sendMessage("You must provide the message to be sent to " + args[0]);
                }
                else
                {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        player.sendMessage(new enumOutput().getEnum("info") + "Message sent to " + ChatColor.ITALIC + target.getDisplayName());
                        target.sendMessage(new enumOutput().getEnum("whisper") + player.getDisplayName() + ": " + ChatColor.GRAY + "" + ChatColor.ITALIC + message);
                    } else player.sendMessage(new enumOutput().getEnum("error") + "Player not found.");
                }
            }
        }
        return true;
    }
}
