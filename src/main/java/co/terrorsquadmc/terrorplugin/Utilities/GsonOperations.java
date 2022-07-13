package co.terrorsquadmc.terrorplugin.Utilities;

import co.terrorsquadmc.terrorplugin.TerrorPlugin;
import com.google.gson.Gson;

import org.bukkit.plugin.Plugin;

import java.io.*;

public class GsonOperations {
    Gson gson;
    Plugin plugin = TerrorPlugin.getPlugin();
    File jsonFile;
    Writer writer;
    Reader reader;

    public GsonOperations() {
        gson = new Gson();
    }

    public void createJsonFile(String name, String uuid) throws IOException {
        grabFile(name, uuid);
        jsonFile.createNewFile();
    }

    public void writeToJsonFile(Object object, boolean overwrite) throws IOException {
        writer = new FileWriter(jsonFile, !overwrite);
        gson.toJson(object, writer);
        writer.flush();
        writer.close();
    }

    public PlayerStats getFromJson(String name, String uuid) throws FileNotFoundException {
        grabFile(name, uuid);
        reader = new FileReader(jsonFile);
        return gson.fromJson(reader, PlayerStats.class);
    }

    private void grabFile(String name, String uuid) {
        jsonFile = new File(plugin.getDataFolder().getAbsolutePath() + "/" + name + "--" + uuid + ".json");
    }
}
