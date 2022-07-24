package co.terrorsquadmc.terrorplugin.Commands;

import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import co.terrorsquadmc.terrorplugin.Utilities.SerializeInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class SerializeInventoryUsage extends SerializeInventory implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        File file;

        if (sender instanceof Player player && args[0].equalsIgnoreCase("writeinv")) {
            Inventory inv = player.getInventory();
            String str = itemStackArrayToBase64(inv.getContents());
            try {
                file = new File(TerrorPlugin.getPlugin().getDataFolder().getAbsolutePath(), "playerinventory.txt");
                FileWriter writer = new FileWriter(file);
                writer.write(str);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (sender instanceof Player player && args[0].equalsIgnoreCase("readinv")) {

            Inventory inv = player.getInventory();
            try {
                file = new File(TerrorPlugin.getPlugin().getDataFolder().getAbsolutePath(), "playerinventory.txt");
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str = "";

                while (bufferedReader.ready()) {
                    str += bufferedReader.readLine();
                }
                inv.setContents(itemStackArrayFromBase64(str));
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
