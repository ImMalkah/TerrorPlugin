package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.Utilities.Book;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveBookCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)
    {
        if (sender instanceof Player player) {
            Book book = new Book("Malkah", "How to Claim Land", player);
            book.initializeBook();
        }
        return true;
    }
}
