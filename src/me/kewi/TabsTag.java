package me.kewi;

import me.kewi.commands.TabsTagCommand;
import me.kewi.listerners.Events;
import me.kewi.manager.TagManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * <h1>TabsTag Alfa</h1>
 *
 * <p>This sample addon demonstrate a custom tag in head and tablist player on Minecraft Server</p>
 *
 * @author Kewilleen Gomes
 * @version 1.1
 * @since 2020-06-02
 */

public class TabsTag extends JavaPlugin {

    private TagManager tagManager;

    @Override
    public void onEnable() {
        checkConfig();
        this.tagManager = new TagManager(this);
        tagManager.starTask();
        getServer().getPluginManager().registerEvents(new Events(this), this);
        getServer().getPluginCommand("tabstag").setExecutor(new TabsTagCommand(this));
    }

    public void checkConfig() {
        File folder = this.getDataFolder();
        if (!folder.exists())
            folder.mkdir();
        File config = new File(folder, "config.yml");
        if (!config.exists())
            saveDefaultConfig();
    }

    public TagManager getTagManager() {
        return tagManager;
    }
}
