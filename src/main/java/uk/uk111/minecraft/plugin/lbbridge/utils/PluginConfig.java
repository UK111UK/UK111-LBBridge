package uk.uk111.minecraft.plugin.lbbridge.utils;

import com.google.gson.Gson;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class PluginConfig {
    private ArrayList<Lootbox> lootboxes = new ArrayList<>();

    public ArrayList<Lootbox> getLootboxes() {
        return this.lootboxes;
    }

    public Optional<Lootbox> getLootboxByName(String name) {
        return lootboxes.stream().filter(lootbox -> lootbox.getName().equals(name)).findFirst();
    }

    public void addLootbox(Lootbox lootbox) {
        this.lootboxes.add(lootbox);
        this.saveConfig();
    }

    public void removeLootbox(String name) {
        this.lootboxes.removeIf(lootbox -> lootbox.getName().equals(name));
        this.saveConfig();
    }

    public void updateLootbox(String name, double price) {
        Lootbox updatedLootbox = new Lootbox(name, price);
        this.lootboxes.removeIf(lootbox -> lootbox.getName().equals(name));
        this.lootboxes.add(updatedLootbox);
        this.saveConfig();
    }

    private void saveConfig() {
        try {
            Gson gson = new Gson();
            Writer writer = new FileWriter(LBBridge.getPlugin(LBBridge.class).getDataFolder() + "/config.json");

            gson.toJson(this, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
