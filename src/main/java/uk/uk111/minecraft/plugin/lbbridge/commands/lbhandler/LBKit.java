package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.PlayerData;

public class LBKit {
    public static String getKit(Player player, String name) {
        String returnMessage = "";

        if (LBBridge.getKitConfig().kitExists(name)) {
            if (player.hasPermission("lbbridge.kits." + name.toLowerCase())) {
                if (LBBridge.getPlayerDataConfig().playerExists(player.getUniqueId())) {
                    PlayerData playerData = LBBridge.getPlayerDataConfig().getPlayerByUUID(player.getUniqueId()).get();

                    if (playerData.hasUsedKit(name.toLowerCase())) { // If they have used the kit
                        if (playerData.canUseKit(name.toLowerCase())) { // If they can use the kit
                            if (playerData.hasEnoughSlots(name.toLowerCase(), player)) {
                                playerData.updateKitUsage(name.toLowerCase());

                                runCommands(LBBridge.getKitConfig().getKitByName(name.toLowerCase()).get().getCommands(), player);

                                returnMessage = ChatColor.LIGHT_PURPLE + "You have received the kit " + ChatColor.GREEN + name.toLowerCase() + ChatColor.LIGHT_PURPLE + "!";
                            } else {
                                returnMessage = ChatColor.LIGHT_PURPLE + "You do not have enough open slots!";
                            }
                        } else {
                            returnMessage = ChatColor.LIGHT_PURPLE + "You can use that kit again at " + ChatColor.GREEN + playerData.getKitUsageString(name.toLowerCase()) + ChatColor.LIGHT_PURPLE + "!";
                        }
                    } else {
                        playerData.addKitUsage(name.toLowerCase());

                        if (playerData.hasEnoughSlots(name.toLowerCase(), player)) {
                            playerData.updateKitUsage(name.toLowerCase());

                            runCommands(LBBridge.getKitConfig().getKitByName(name.toLowerCase()).get().getCommands(), player);

                            returnMessage = ChatColor.LIGHT_PURPLE + "You have received the kit " + ChatColor.GREEN + name.toLowerCase() + ChatColor.LIGHT_PURPLE + "!";
                        } else {
                            returnMessage = ChatColor.LIGHT_PURPLE + "You do not have enough open slots!";
                        }
                    }
                } else {
                    LBBridge.getPlayerDataConfig().addPlayer(player.getUniqueId(), name.toLowerCase());
                    PlayerData playerData = LBBridge.getPlayerDataConfig().getPlayerByUUID(player.getUniqueId()).get();
                    playerData.addKitUsage(name.toLowerCase());

                    if (playerData.hasEnoughSlots(name.toLowerCase(), player)) {
                        playerData.updateKitUsage(name.toLowerCase());

                        runCommands(LBBridge.getKitConfig().getKitByName(name.toLowerCase()).get().getCommands(), player);

                        returnMessage = ChatColor.LIGHT_PURPLE + "You have received the kit " + ChatColor.GREEN + name.toLowerCase() + ChatColor.LIGHT_PURPLE + "!";
                    } else {
                        returnMessage = ChatColor.LIGHT_PURPLE + "You do not have enough open slots!";
                    }
                }
            } else {
                returnMessage = ChatColor.LIGHT_PURPLE + "You do not have permission to get kit: " + ChatColor.GREEN + name + ChatColor.LIGHT_PURPLE + "!";
            }
        } else {
            returnMessage = ChatColor.LIGHT_PURPLE + "That kit does not exist!";
        }

        return returnMessage;
    }

    private static void runCommands(String[] commands, Player player) {
        ConsoleCommandSender console = LBBridge.getPlugin(LBBridge.class).getServer().getConsoleSender();

        for (String cmd : commands) {
            Bukkit.dispatchCommand(console, cmd.replace("%playername%", player.getName()));
        }
    }
}
