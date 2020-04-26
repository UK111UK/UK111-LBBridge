package uk.uk111.minecraft.plugin.lbbridge.utils;

public class Kit {
    private String kitName;
    private String[] commands;
    private int delay;

    public String getName() {
        return this.kitName;
    }

    public String[] getCommands() {
        return this.commands;
    }

    public int getDelay() {
        return this.delay;
    }
}
