package co.terrorsquadmc.terrorplugin;

import org.bukkit.ChatColor;

enum ConsoleOutput
{
    INFO, WHISPER, ERROR, COMMAND
}

public class enumOutput
{
    public String getEnum(String name)
    {
        if (name.equalsIgnoreCase("info"))
        {
            return ChatColor.GOLD + "" + ChatColor.BOLD + "[" + ChatColor.RESET + "" +
                    ChatColor.GOLD + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + ConsoleOutput.INFO + ChatColor.RESET +
                    ChatColor.GOLD + "" + ChatColor.BOLD + "]: " + ChatColor.RESET;
        }
        else if (name.equalsIgnoreCase("whisper"))
        {
            return ChatColor.GOLD + "" + ChatColor.BOLD + "[" + ChatColor.RESET + "" +
                    ChatColor.GOLD + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + ConsoleOutput.WHISPER + ChatColor.RESET +
                    ChatColor.GOLD + "" + ChatColor.BOLD + "]: " + ChatColor.RESET;
        }
        else if (name.equalsIgnoreCase("error"))
        {
            return ChatColor.RED + "" + ChatColor.BOLD + "[" + ChatColor.RESET + "" +
                    ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + ConsoleOutput.ERROR + ChatColor.RESET +
                    ChatColor.RED + "" + ChatColor.BOLD + "]: " + ChatColor.RESET;
        }
        else if (name.equalsIgnoreCase("command"))
        {
            return ChatColor.AQUA + "" + ChatColor.BOLD + "[" + ChatColor.RESET + "" +
                    ChatColor.AQUA + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + ConsoleOutput.COMMAND + ChatColor.RESET +
                    ChatColor.AQUA + "" + ChatColor.BOLD + "]: " + ChatColor.RESET;
        }
        return "You must choose correct enumeration...";
    }
}
