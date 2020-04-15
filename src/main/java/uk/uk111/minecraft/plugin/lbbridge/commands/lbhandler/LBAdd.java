package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.ChatColor;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.Lootbox;

public class LBAdd {
    public static String addLootbox(String name, double price) {
        String returnMessage = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            returnMessage = "That lootbox already exists!";
        } else {
            LBBridge.getPluginConfig().addLootbox(new Lootbox(name, price));
            returnMessage = "Added lootbox " + ChatColor.LIGHT_PURPLE + name + ChatColor.WHITE + "!";
        }

        return returnMessage;
    }
}
