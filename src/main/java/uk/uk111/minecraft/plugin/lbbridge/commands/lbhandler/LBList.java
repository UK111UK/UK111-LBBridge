package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import net.md_5.bungee.api.ChatColor;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.Lootbox;

public class LBList {
    public static String listLootboxes() {
        StringBuilder lootboxList = new StringBuilder();

        if (LBBridge.getPluginConfig().getLootboxes().size() > 0) {
            for (Lootbox lootbox : LBBridge.getPluginConfig().getLootboxes()) {
                if (lootboxList.toString().equals("")) {
                    lootboxList = new StringBuilder("Lootbox List:\n" + ChatColor.LIGHT_PURPLE + lootbox.getName() + ChatColor.WHITE + ": " + ChatColor.GREEN + "$" + lootbox.getPrice());
                } else {
                    lootboxList.append("\n" + ChatColor.LIGHT_PURPLE + lootbox.getName() + ChatColor.WHITE + ": " + ChatColor.GREEN + "$" + lootbox.getPrice());
                }
            }
        } else {
            lootboxList = new StringBuilder("None!");
        }

        return lootboxList.toString();
    }
}
