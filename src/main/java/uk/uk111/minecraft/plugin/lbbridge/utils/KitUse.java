package uk.uk111.minecraft.plugin.lbbridge.utils;

import java.time.LocalDateTime;

public class KitUse {
    private String kitName;
    private LocalDateTime used;

    public KitUse(String name, LocalDateTime used) {
        this.kitName = name;
        this.used = used;
    }

    public String getName() {
        return this.kitName;
    }

    public LocalDateTime getUsed() {
        return this.used;
    }

    public void resetUsed() {
        this.used = LocalDateTime.now();
    }
}
