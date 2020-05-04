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
                    "/lb add [name] [price] - Adds a lootbox\n" +
                    "/lb buy [name] - Buy a lootbox with in-game currency\n" +
                    "/lb list - List available lootboxes\n" +
                    "/lb remove [name] - Removes a lootbox\n" +
                    "/lb update [name] [price] - Updates a lootbox\n" +
                    "/lb kits - List Kits\n" +
                    "/lb kit [name] - Get a lootbox kit");
        } else {
            switch (args[0].toLowerCase()) {
                case "add": {
                    if (player.isOp()) {
                        if (args.length != 3) {
                            sendLBMessage(player, "Incorrect arguments! Usage: /lb add [name] [price]]");
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
                            sendLBMessage(player, "Incorrect arguments! Usage: /lb remove [name]");
                        } else {
                            sendLBMessage(player, LBRemove.removeLootbox(args[1]));
                        }
                    } else {
                        sendLBMessage(player, "Only Server Operators have access to this command!");
                    }
                    break;
                }
                case "buy": {
                    int amount = 1;
                    if (args.length == 3)
                    {
                        amount = Integer.parseInt(args[2]);
                    }
                    if (args.length != 2 || args.length != 3)
                    {
                        sendLBMessage(player, "Incorrect arguments! Usage: /lb buy [name] [amount]");
                    }
                    else {
                        sendLBMessage(player, LBBuy.buyLootbox(args[1], player, amount));
                    }
                    break;
                }
                case "update": {
                    if (player.isOp()) {
                        if (args.length != 3) {
                            sendLBMessage(player, "Incorrect arguments! Usage: /lb update [name] [price]");
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
                case "kits": {
                    sendLBMessage(player, LBKits.getLootboxList());
                    break;
                }
                case "kit": {
                    if (args.length != 2) {
                        sendLBMessage(player, "Incorrect arguments! Usage: /lb kit [name]");
                    } else {
                        sendLBMessage(player, LBKit.getKit(player, args[1]));
                    }
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
