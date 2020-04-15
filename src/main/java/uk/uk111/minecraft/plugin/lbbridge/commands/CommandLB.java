package uk.uk111.minecraft.plugin.lbbridge.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler.*;

public class CommandLB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            sendLBMessage(player, "Commands:\n" +
                    "/lb add - Adds a lootbox\n" +
                    "/lb buy - Buy a lootbox with in-game currency\n" +
                    "/lb list - List available lootboxes\n" +
                    "/lb remove - Removes a lootbox\n" +
                    "/lb update - Updates a lootbox\n");
        } else {
            switch (args[0].toLowerCase()) {
                case "add": {
                    if (player.isOp()) {
                        if (args.length != 3) {
                            sendLBMessage(player, "Incorrect arguments! Usage: /lb add LOOTBOX_NAME PRICE");
                        } else {
                            sendLBMessage(player, LBAdd.addLootbox(args[1], Double.parseDouble(args[2])));
                        }
                    } else {
                        sendLBMessage(player, "Only Server Operators have access to this command!");
                    }
                    break;
                }
                case "remove": {
                    if (player.isOp()) {
                        if (args.length != 2) {
                            sendLBMessage(player, "Incorrect arguments! Usage: /lb remove LOOTBOX_NAME");
                        } else {
                            sendLBMessage(player, LBRemove.removeLootbox(args[1]));
                        }
                    } else {
                        sendLBMessage(player, "Only Server Operators have access to this command!");
                    }
                    break;
                }
                case "buy": {
                    if (args.length != 2) {
                        sendLBMessage(player, "Incorrect arguments! Usage: /lb buy LOOTBOX_NAME");
                    } else {
                        sendLBMessage(player, LBBuy.buyLootbox(args[1], player));
                    }
                    break;
                }
                case "update": {
                    if (player.isOp()) {
                        if (args.length != 3) {
                            sendLBMessage(player, "Incorrect arguments! Usage: /lb update LOOTBOX_NAME PRICE");
                        } else {
                            sendLBMessage(player, LBUpdate.updateLootbox(args[1], Double.parseDouble(args[2])));
                        }
                    } else {
                        sendLBMessage(player, "Only Server Operators have access to this command!");
                    }
                    break;
                }
                case "list": {
                    sendLBMessage(player, LBList.listLootboxes());
                    break;
                }
                default: {
                    sendLBMessage(player, "Not a valid command.");
                    break;
                }
            }
        }

        return true;
    }

    private void sendLBMessage(Player player, String message) {
        player.sendMessage(ChatColor.DARK_RED + "[LBBridge]" +
                ChatColor.WHITE + ": " + message);
    }
}
