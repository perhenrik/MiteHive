package no.perhenrik.mitehive;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by perhenrik on 16.07.2016.
 * Main spigot plugin class
 */
public class MiteHivePlugin extends JavaPlugin {
    static final String DEBUG = "Debug";
    static final String ENABLED_WORLD = "World";

    @Override
    public void onLoad() {
        saveDefaultConfig();
        reloadConfig();
        info(String.format("%s: %s", DEBUG, getConfig().getBoolean(DEBUG, false)));
        info(String.format("%s: %s", ENABLED_WORLD, getConfig().getString(ENABLED_WORLD, "world")));
    }

    void reload() {
        reloadConfig();
    }

    String getWorld() {
        return getConfig().getString(ENABLED_WORLD,"world");
    }

    boolean getDebug() {
        return getConfig().getBoolean(DEBUG, false);
    }

    void setDebug(boolean d) {
        getConfig().set(DEBUG, d);
        saveConfig();
    }

    private void info(String msg) {
        getLogger().info(msg);
    }

    /**
     * @param msg
     */
    void debug(String msg) {
        if(getDebug()) {
            getLogger().info("DEBUG: " + msg);
        }
    }

    void severe(String msg) {
        getLogger().severe(msg);
    }

    @Override
    public void onEnable() {
        this.getCommand("mitehive").setExecutor(new MiteHiveCommandExecutor(this));
    }

    @Override
    public void onDisable() {

    }

}
