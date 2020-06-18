package me.kewi.commands;

import me.kewi.TabsTag;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TabsTagCommand implements CommandExecutor {

    private final TabsTag tabsTag;

    public TabsTagCommand(TabsTag tabsTag) {
        this.tabsTag = tabsTag;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!commandSender.hasPermission("tabstag.*")) {
            commandSender.sendMessage("§cSorry, but you don't have permission to execute this command!");
            return true;
        }
        if (args.length == 0) {
            commandSender.sendMessage("§b[TabsTag] Info:");
            commandSender.sendMessage("§bThank you to use this plugin :)");
            commandSender.sendMessage("§b");
            commandSender.sendMessage("§b/tt reload - Use this command to reload the tags.");
            commandSender.sendMessage("§b");
            commandSender.sendMessage("§bDeveloped by DEVKEWI");
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            tabsTag.getTagManager().reload();
            commandSender.sendMessage("§b[TabsTag] successfully reloaded");
            return true;
        }
        return true;
    }
}
