package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.ChatColor;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

public class LBRemove {
    public static String removeLootbox(String name) {
        String returnMessage = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            LBBridge.getPluginConfig().removeLootbox(name);
            returnMessage = "Removed lootbox " + ChatColor.LIGHT_PURPLE + name + ChatColor.WHITE + "!";
        } else {
            returnMessage = "Lootbox " + ChatColor.LIGHT_PURPLE + name + ChatColor.WHITE + " not found!";
        }

        return returnMessage;
    }
}
