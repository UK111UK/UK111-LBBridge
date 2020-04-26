package uk.uk111.minecraft.plugin.lbbridge.utils;

import com.google.gson.Gson;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class PlayerDataConfig {
    private ArrayList<PlayerData> playerData = new ArrayList<>();

    public boolean playerExists(UUID uuid) {
        return this.playerData.stream().anyMatch(p -> p.getPlayerUUID().equals(uuid));
    }

    public Optional<PlayerData> getPlayerByUUID(UUID uuid) {
        return this.playerData.stream().filter(p -> p.getPlayerUUID().equals(uuid)).findFirst();
    }

    public void addPlayer(UUID uuid, String kitName) {
        this.playerData.add(new PlayerData(uuid));
        this.getPlayerByUUID(uuid).get().addKitUsage(kitName);
        this.saveConfig();
    }

    public void saveConfig() {
        try {
            Gson gson = new Gson();
            Writer writer = new FileWriter(LBBridge.getPlugin(LBBridge.class).getDataFolder() + "/playerData.json");

            gson.toJson(this, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
