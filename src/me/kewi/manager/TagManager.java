package me.kewi.manager;

import me.kewi.TabsTag;
import me.kewi.api.PlayerRemoveTagEvent;
import me.kewi.api.PlayerUpdateTagEvent;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Tag Manager system
 *
 * The manager implements in scoreboard the teams making a simple tag
 */
public class TagManager {

    private final TabsTag tabsTag;
    private Scoreboard scoreboard;

    public TagManager(TabsTag tabsTag) {
        this.tabsTag = tabsTag;
        //call initialize, where do updates
        initialize();
    }

    /**
     * This method is used on start plugin.
     *
     * It get Main Scoreboard to team, removed all exists to make a update to all players
     */
    public void initialize() {
        //indetify and make configuration
        tabsTag.checkConfig();
        //get main scoreboard
        this.scoreboard = tabsTag.getServer().getScoreboardManager().getMainScoreboard();
        //remove all teams
        removeAllTeams();
        //update teams to all players in server
        for (Player player : getPlayers())
            update(player);
    }

    /**
     * Update tag player using scoreboard team
     *
     * To update tag player is require a permission predefined as "tabstag" more a
     * name tag in configuration file.
     *
     * Get a tag using tabstag.YOUR_TAG
     *
     * @param player Player bukkit
     */
    public void update(Player player) {
        //initialize the tag
        String tag = null;
        //foreach the player tag
        for (String pathTag : tabsTag.getConfig().getConfigurationSection("Tags").getKeys(false)) {
            //check has permission player and unlike default
            if (player.hasPermission("tabstag." + pathTag) && !pathTag.equalsIgnoreCase("default")) {
                tag = getTag("Tags." + pathTag);
                break;
            }
        }
        //if don't find the tag, default is setted
        if (tag == null)
            tag = getTag("Tags.default");
        //the maximun string is 16 character, so, identify is longer 16 and remove other string. To team too.
        if (tag.length() > 16)
            tag = tag.substring(0, 16);
        String teamName = "TT" + player.getName().toLowerCase();
        if (teamName.length() > 16)
            teamName = teamName.substring(0, 16);
        //getting the scoreboard and register a new team to player
        Team team = scoreboard.getTeam(teamName);
        if (team == null)
            team = scoreboard.registerNewTeam(teamName);
        //create a custom listerner to other developer using the plugin
        PlayerUpdateTagEvent playerUpdateTagEvent = new PlayerUpdateTagEvent(player, scoreboard, team, tag);
        //calling event of plugin structure
        tabsTag.getServer().getPluginManager().callEvent(playerUpdateTagEvent);
        //check is other developer cancel the event
        if (playerUpdateTagEvent.isCancelled())
            return;
        //otherelse set the tag or prefix, whatever
        team.setPrefix(tag);
        //adding player to new list
        team.addPlayer(player);
    }

    /**
     * Get prefix
     *
     * Get tag from configuration file
     *
     * @param path path to configuration file
     * @return string
     */
    public String getTag(String path) {
        return tabsTag.getConfig().getString(path).replace("&", "§");
    }

    private Player[] getPlayers() {
        return tabsTag.getServer().getOnlinePlayers();
    }

    /**
     * Remove tags
     *
     * Remove all tags
     *
     * @param player Bukkit player
     */
    public void remove(Player player) {
        Team team = scoreboard.getPlayerTeam(player);
        if (team != null) {
            PlayerRemoveTagEvent playerRemoveTagEvent = new PlayerRemoveTagEvent(player, scoreboard, team, team.getPrefix());
            tabsTag.getServer().getPluginManager().callEvent(playerRemoveTagEvent);
            if (playerRemoveTagEvent.isCancelled())
                return;
            team.removePlayer(player);
        }
    }

    /**
     *
     * Remove teams
     *
     * Remove all teams from server
     *
     */
    public void removeAllTeams() {
        for (Team team : scoreboard.getTeams())
            if (team.getName().startsWith("TT"))
                team.unregister();
    }
}
