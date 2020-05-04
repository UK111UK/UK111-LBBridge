package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.Lootbox;

public class LBBuy {
    public static String buyLootbox(String name, Player player, int amount) {
        String returnMessage = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            Lootbox lootbox = LBBridge.getPluginConfig().getLootboxByName(name).get();
            if (LBBridge.getEcononmy().getBalance(player) >= (lootbox.getPrice() * amount)) {
                LBBridge.getEcononmy().withdrawPlayer(player, lootbox.getPrice());
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), String.format("elb give %s %s %d", player.getName(), name, amount));
                returnMessage = String.format("You have purchased " + ChatColor.LIGHT_PURPLE + "%d x %s " +
                        ChatColor.WHITE + "for " +
                        ChatColor.GREEN + "$%s" + ChatColor.WHITE + "!",amount, lootbox.getName(), lootbox.getPrice() * amount);
            } else {
                returnMessage = "You do not have enough money for that!";
            }
        } else {
            returnMessage = "Could not find that lootbox!";
        }

        return returnMessage;
    }
}
