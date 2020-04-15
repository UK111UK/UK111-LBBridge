package uk.uk111.minecraft.plugin.lbbridge.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler.LBAdd;
import uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler.LBBuy;
import uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler.LBRemove;
import uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler.LBUpdate;

public class CommandLB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        LBBridge.getPlugin(LBBridge.class).getServer().getOfflinePlayer(player.getUniqueId());
        player.sendMessage(LBBridge.getEcononmy().getBalance(player) + "");
        player.sendMessage(LBBridge.getPluginConfig().toString());

        if (args.length == 0) {

        } else {
            switch (args[0].toLowerCase()) {
                case "add": {
                    if (args.length != 3) {
                        player.sendMessage("Incorrect arguments! Usage: /lb add LOOTBOX_NAME PRICE");
                    } else {
                        player.sendMessage(LBAdd.AddLootbox(args[1], Double.parseDouble(args[2])));
                    }
                    break;
                }
                case "remove": {
                    if (args.length != 2) {
                        player.sendMessage("Incorrect arguments! Usage: /lb remove LOOTBOX_NAME");
                    } else {
                        player.sendMessage(LBRemove.RemoveLootbox(args[1]));
                    }
                    break;
                }
                case "buy": {
                    if (args.length != 2) {
                        player.sendMessage("Incorrect arguments! Usage: /lb buy LOOTBOX_NAME");
                    } else {
                        player.sendMessage(LBBuy.BuyLootbox(args[1], player));
                    }
                    break;
                }
                case "update": {
                    if (args.length != 3) {
                        player.sendMessage("Incorrect arguments! Usage: /lb update LOOTBOX_NAME PRICE");
                    } else {
                        player.sendMessage(LBUpdate.UpdateLootbox(args[1], Double.parseDouble(args[2])));
                    }
                    break;
                }
                default: {
                    player.sendMessage("Not a valid command.");
                    break;
                }
            }
        }

        return true;
    }
}
