package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.ChatColor;
import org.yaml.snakeyaml.Yaml;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.Lootbox;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class LBAdd {
    public static String addLootbox(String name, double price) {
        String returnMessage = "";

        try {
            if (eliteLootboxExists(name)) {
                if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
                    returnMessage = "That lootbox already exists!";
                } else {
                    LBBridge.getPluginConfig().addLootbox(new Lootbox(name, price));
                    returnMessage = "Added lootbox " + ChatColor.LIGHT_PURPLE + name + ChatColor.WHITE + "!";
                }
            } else {
                returnMessage = "Lootbox not found in EliteLootbox!";
            }

            return returnMessage;
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private static boolean eliteLootboxExists(String name) throws IOException {
        Yaml yaml = new Yaml();

        Map<String, Object> obj = yaml.load(new String(Files.readAllBytes(Paths.get(LBBridge.getPlugin(LBBridge.class).getDataFolder() + "/../EliteLootbox/lootbox.yml"))));
        return ((Map<String, Object>) obj.get("Lootboxes")).containsKey(name);
    }
}
