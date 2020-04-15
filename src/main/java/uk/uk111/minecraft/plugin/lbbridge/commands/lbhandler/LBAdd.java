package uk.uk111.minecraft.plugin.lbbridge.commands.lbhandler;

import com.google.gson.Gson;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;
import uk.uk111.minecraft.plugin.lbbridge.utils.Lootbox;
import uk.uk111.minecraft.plugin.lbbridge.utils.PluginConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class LBAdd {
    public static String AddLootbox(String name, double price) {
        String returnString = "";

        if (LBBridge.getPluginConfig().getLootboxByName(name).isPresent()) {
            returnString = "That lootbox already exists!";
        } else {
            LBBridge.getPluginConfig().addLootbox(new Lootbox(name, price));
            returnString = "Lootbox added!";
        }

        return returnString;
    }
}
