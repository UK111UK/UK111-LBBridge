package uk.uk111.minecraft.plugin.lbbridge.utils;

import org.bukkit.entity.Player;
import uk.uk111.minecraft.plugin.lbbridge.LBBridge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class PlayerData {
    private UUID playerUUID;
    private ArrayList<KitUse> usedKits = new ArrayList<>();

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public boolean hasUsedKit(String name) {
        return this.usedKits.stream().anyMatch(kit -> kit.getName().toLowerCase().equals(name.toLowerCase()));
    }

    public void addKitUsage(String name) {
        this.usedKits.add(new KitUse(name, LocalDateTime.now()));
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void updateKitUsage(String name) {
        int index = this.usedKits.indexOf(this.usedKits.stream().filter(k -> k.getName().toLowerCase().equals(name.toLowerCase())).findFirst().get());
        KitUse kit = this.usedKits.get(index);
        kit.resetUsed();
        this.usedKits.set(index, kit);
        LBBridge.getPlayerDataConfig().saveConfig();
    }

    public boolean canUseKit(String name) {
        LocalDateTime useTime = this.usedKits.stream().filter(kit -> kit.getName().toLowerCase().equals(name.toLowerCase())).findFirst().get().getUsed();
        Kit kit = LBBridge.getKitConfig().getKitByName(name.toLowerCase()).get();
        return useTime.plusSeconds(kit.getDelay()).isBefore(LocalDateTime.now());
    }

    public String getKitUsageString(String name) {
        LocalDateTime useTime = this.usedKits.stream().filter(kit -> kit.getName().toLowerCase().equals(name.toLowerCase())).findFirst().get().getUsed();
        LocalDateTime useTimeAgain = useTime.plusSeconds(LBBridge.getKitConfig().getKitByName(name.toLowerCase()).get().getDelay());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return useTimeAgain.format(formatter);
    }

    public boolean hasEnoughSlots(String name, Player player) {
        int count = LBBridge.getKitConfig().getKitByName(name.toLowerCase()).get().getCommands().length; // This is stupid...
        count += (count / 2); // For Food Lootbox that doesn't stack...

        long freeSlots = Arrays.stream(player.getInventory().getContents()).filter(i -> i == null || i.getType().isAir()).count();

        return freeSlots >= count;
    }
}
