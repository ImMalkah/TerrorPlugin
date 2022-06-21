package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.Utilities.Book;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveBookCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player player) {
            Book book = new Book("Malkah", "Getting Started", player);
            book.initializeBook();
        }
        return false;
    }
}