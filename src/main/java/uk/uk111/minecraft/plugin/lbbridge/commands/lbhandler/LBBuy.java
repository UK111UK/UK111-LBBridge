package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.Lootbox;

public class LBBuy {
    public static String buyLootbox(String name, Player player) {
        String returnMessage = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            Lootbox lootbox = LBBridge.getPluginConfig().getLootboxByName(name).get();
            if (LBBridge.getEcononmy().getBalance(player) >= lootbox.getPrice()) {
                LBBridge.getEcononmy().withdrawPlayer(player, lootbox.getPrice());
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), String.format("elb give %s %s", player.getName(), name));
                returnMessage = String.format("You have purchased " + ChatColor.LIGHT_PURPLE + "%s " +
                        ChatColor.WHITE + "for " +
                        ChatColor.GREEN + "$%s" + ChatColor.WHITE + "!", lootbox.getName(), lootbox.getPrice());
            } else {
                returnMessage = "You do not have enough money for that!";
            }
        } else {
            returnMessage = "Could not find that lootbox!";
        }

        return returnMessage;
    }
}
