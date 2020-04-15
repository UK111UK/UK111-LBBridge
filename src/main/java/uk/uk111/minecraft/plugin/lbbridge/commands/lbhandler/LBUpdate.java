package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.ChatColor;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

public class LBUpdate {
    public static String updateLootbox(String name, double price) {
        String returnMessage = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            LBBridge.getPluginConfig().updateLootbox(name, price);
            returnMessage = "Lootbox " + ChatColor.LIGHT_PURPLE + name + ChatColor.WHITE + " updated!";
        } else {
            returnMessage = "Lootbox " + ChatColor.LIGHT_PURPLE + name + ChatColor.WHITE + " not found!";
        }

        return returnMessage;
    }
}
