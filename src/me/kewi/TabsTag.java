package me.kewi;

import me.kewi.commands.TabsTagCommand;
import me.kewi.listerners.Events;
import me.kewi.manager.TagManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * TabsTag v1.0-ALPHA
 *
 * This sample addon demonstrate a custom tag in head and tablist player on Minecraft Server
 *
 *
 * @author Kewilleen Gomes
 * @version 1.0
 * @since 2020-06-02
 *
 */

public class TabsTag extends JavaPlugin {

    private static TabsTag instance;
    private TagManager tagManager;

    public static TabsTag getInstance() {
        return instance;
    }

    /**
     * Define the start addon parameters
     *
     * An instance to call plugin reference
     */
    @Override
    public void onEnable() {
        instance = this;
        //start the tagmanager to manipule javaplugin system
        this.tagManager = new TagManager(this);
        //register join, quit and kick listerners
        getServer().getPluginManager().registerEvents(new Events(this), this);
        //register command to reload the system
        getServer().getPluginCommand("tabstag").setExecutor(new TabsTagCommand(this));
    }

    /**
     * Disabilitin the system
     *
     * Remove all tags created
     */
    @Override
    public void onDisable() {
        tagManager.removeAllTeams();
    }

    /**
     * Check file is created
     *
     * This determine create folder and saveDefaultConfig, however get automatic config
     */
    public void checkConfig() {
        //get folder plugin
        File folder = instance.getDataFolder();
        //make a dir if not exists
        if (!folder.exists())
            folder.mkdir();
        //get a config "template" file
        File config = new File(folder, "config.yml");
        if (!config.exists())
            saveDefaultConfig();
    }

    /**
     * Call a Tag Manager system
     *
     * Where th system working
     *
     * @return TagManager
     */
    public TagManager getTagManager() {
        return tagManager;
    }
}
