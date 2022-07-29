package co.terrorsquadmc.terrorplugin.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String author, title;
    private Player player;
    private List<String> lore = new ArrayList<>();
    ItemStack book;
    BookMeta bookMeta;


    public Book(String author, String title, Player player) {
        this.author = author;
        this.title = title;
        this.player = player;

        book = new ItemStack(Material.WRITTEN_BOOK);
        bookMeta = (BookMeta) book.getItemMeta();

        bookMeta.setAuthor(ChatColor.DARK_PURPLE + author);
        bookMeta.setTitle(ChatColor.RED + "" + ChatColor.BOLD + title);

        addPage(ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome to Terror Squad SMP!\n" + ChatColor.RESET + "\nThe purpose of this book is to help you get started with useful commands. " +
                "Page 2 shows you how to claim your land so that you can protect yourself and your loot against griefers. Go there now!");
        addPage("""
                    Step 1) Grab a wooden shovel and a stick
                    
                    Step 2) Right click a corner with the shovel to mark the start of the claim
                    
                    Step 3) Right click in another corner to mark the end of the claim
                    """);
        addPage(
                ChatColor.GOLD + "/trust: " + ChatColor.RESET + "gives permission to edit in your claim\n" +
                        ChatColor.GOLD + "/AccessTrust: " + ChatColor.RESET + "permission to use buttons, levers, and beds\n" +
                        ChatColor.GOLD + "/ContainerTrust: " + ChatColor.RESET + "permission to use containers such as chests\n" +
                        ChatColor.GOLD + "/TrustList: " + ChatColor.RESET + "shows a list of trusted players\n");
    }

    public void addPage(String data) {
        lore.add(data);
    }

    public void initializeBook() {
        bookMeta.setPages(lore);
        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
