package uk.uk111.minecraft.plugin.lbbridge.utils;

import java.util.ArrayList;
import java.util.Optional;

public class KitConfig {
    private ArrayList<Kit> kits = new ArrayList<>();

    public ArrayList<Kit> getKits() {
        return this.kits;
    }

    public boolean kitExists(String name) {
        return kits.stream().anyMatch(kit -> kit.getName().toLowerCase().equals(name.toLowerCase()));
    }

    public Optional<Kit> getKitByName(String name) {
        return kits.stream().filter(kit -> kit.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
    }
}
