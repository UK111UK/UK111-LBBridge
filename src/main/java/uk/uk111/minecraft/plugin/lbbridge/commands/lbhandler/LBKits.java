package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.ChatColor;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

public class LBKits {
    public static String getLootboxList() {
        StringBuilder returnMessage = new StringBuilder(ChatColor.GREEN + "Kits:\n" + ChatColor.LIGHT_PURPLE);

        LBBridge.getKitConfig().getKits().forEach(kit -> {
            returnMessage.append(kit.getName() + "\n");
        });

        return returnMessage.toString();
    }
}
