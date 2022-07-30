package co.terrorsquadmc.terrorplugin.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Hub implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.performCommand("mvtp PixeledHub");
            player.sendMessage(Component.text(ChatColor.GOLD + "You've been teleported to the " + ChatColor.RESET + "" + ChatColor.RED + "hub"));
        }
        return true;
    }
}
