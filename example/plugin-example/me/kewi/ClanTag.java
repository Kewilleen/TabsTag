package me.kewi;

import me.kewi.listerner.Events;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ClanTag extends JavaPlugin {

    private SimpleClans simpleClans;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        /* Identify plugin to enable hook
         * This example i'll use the SimpleClans to get TAG
         */
        if (getPlugin("SimpleClans") == null || getPlugin("TabsTag") == null)
            return;
        // Instance SimpleClans
        this.simpleClans = (SimpleClans) getPlugin("SimpleClans");
        getServer().getPluginManager().registerEvents(new Events(this), this);
    }

    // Method to hook with plugin
    private Plugin getPlugin(String pluginName) {
        Plugin plugin = getServer().getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            getServer().getPluginManager().disablePlugin(this);
            return null;
        }
        return plugin;
    }

    // Get SimpleClan plugin
    public SimpleClans getSimpleClans() {
        return simpleClans;
    }
}
