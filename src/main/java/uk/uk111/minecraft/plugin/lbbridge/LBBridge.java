package uk.uk111.minecraft.plugin.lbbridge;

import com.google.gson.Gson;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import uk.uk111.minecraft.plugin.lbbridge.commands.CommandLB;
import uk.uk111.minecraft.plugin.lbbridge.utils.KitConfig;
import uk.uk111.minecraft.plugin.lbbridge.utils.PlayerDataConfig;
import uk.uk111.minecraft.plugin.lbbridge.utils.PluginConfig;

import java.io.*;
import java.util.logging.Logger;

public class LBBridge extends JavaPlugin {
    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static PluginConfig config = null;
    private static PlayerDataConfig playerDataConfig = null;
    private static KitConfig kitConfig = null;

    @Override
    public void onEnable() {
        sendLog("Enabled!");

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Try to load config
        try {
            loadConfig();
            loadKitConfig();
            loadPlayerData();
        } catch (IOException ex) {
            log.severe(ex.getMessage());
            return;
        }

        // Register Commands
        this.getCommand("lb").setExecutor(new CommandLB());
    }

    @Override
    public void onDisable() {
        sendLog("Disabled!");
    }

    private void sendLog(String log) {
        getLogger().info("[UK111-LBBridge] " + log);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private void loadConfig() throws IOException {
        File configDirectory = new File(getDataFolder(), "");
        File configFile = new File(getDataFolder() + "/config.json");
        Gson gson = new Gson();

        if (!configDirectory.exists()) {
            configDirectory.mkdir();
        }

        if (!configFile.exists()) {
            config = new PluginConfig();
            Writer writer = new FileWriter(getDataFolder() + "/config.json");
            gson.toJson(new PluginConfig(), writer);
            writer.flush();
            writer.close();
        }

        config = gson.fromJson(new FileReader(getDataFolder() + "/config.json"), PluginConfig.class);
    }

    private void loadPlayerData() throws IOException {
        File configDirectory = new File(getDataFolder(), "");
        File playerData = new File(getDataFolder() + "/playerData.json");
        Gson gson = new Gson();

        if (!configDirectory.exists()) {
            configDirectory.mkdir();
        }

        if (!playerData.exists()) {
            playerDataConfig = new PlayerDataConfig();
            Writer writer = new FileWriter(getDataFolder() + "/playerData.json");
            gson.toJson(new PlayerDataConfig(), writer);
            writer.flush();
            writer.close();
        }

        playerDataConfig = gson.fromJson(new FileReader(getDataFolder() + "/playerData.json"), PlayerDataConfig.class);
    }

    private void loadKitConfig() throws IOException {
        File configDirectory = new File(getDataFolder(), "");
        File kitConfigFile = new File(getDataFolder() + "/kits.json");
        Gson gson = new Gson();

        if (!configDirectory.exists()) {
            configDirectory.mkdir();
        }

        if (!kitConfigFile.exists()) {
            kitConfig = new KitConfig();
            Writer writer = new FileWriter(getDataFolder() + "/kits.json");
            gson.toJson(new KitConfig(), writer);
            writer.flush();
            writer.close();
        }

        kitConfig = gson.fromJson(new FileReader(getDataFolder() + "/kits.json"), KitConfig.class);
    }

    public static Economy getEcononmy() {
        return econ;
    }

    public static PluginConfig getPluginConfig() {
        return config;
    }

    public static KitConfig getKitConfig() {
        return kitConfig;
    }

    public static PlayerDataConfig getPlayerDataConfig() {
        return playerDataConfig;
    }
}
