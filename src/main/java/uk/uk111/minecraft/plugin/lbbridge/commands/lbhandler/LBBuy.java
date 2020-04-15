package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

public class LBBuy {
    public static String BuyLootbox(String name, Player player) {
        String returnMessage = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            if (LBBridge.getEcononmy().getBalance(player) >= LBBridge.getPluginConfig().getLootboxByName(name).get().getPrice()) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), String.format("elb give %s %s", player.getName(), name));
            } else {
                returnMessage = "You do not have enough money for that!";
            }
        } else {
            returnMessage = "Could not find that lootbox!";
        }

        return returnMessage;
    }
}
