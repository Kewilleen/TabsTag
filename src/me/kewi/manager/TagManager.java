package me.kewi.manager;

import me.kewi.TabsTag;
import me.kewi.api.PlayerRemoveTagEvent;
import me.kewi.api.PlayerSetTagEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Set;

/**
 * <h1>Tag Manager</h1>
 *
 * <p>The manager insert in Team Scoreboard of player, making a simple tag and team</p>
 */
public class TagManager {

    private final TabsTag tabsTag;
    private final Scoreboard scoreboard;

    public TagManager(TabsTag tabsTag) {
        this.tabsTag = tabsTag;
        this.scoreboard = tabsTag.getServer().getScoreboardManager().getMainScoreboard();
        setHealth();
    }

    private Set<String> getTags() {
        return tabsTag.getConfig().getConfigurationSection("Tags").getKeys(false);
    }

    /**
     * Player has team
     *
     * @param nickname Nickname from player to check has team
     * @return Whether player name has team
     */
    public boolean hasTeam(String nickname) {
        return getTeam(nickname) != null;
    }

    /**
     * Get current team from player
     *
     * @param nickname Nickname from player to get in scoreboard team
     * @return Team from player
     */
    public Team getTeam(String nickname) {
        return scoreboard.getTeam(nickname.toLowerCase());
    }

    /**
     * Update team by player
     *
     * @param player Add team to player
     */
    public void updateTeam(Player player) {
        String nickname = player.getName().toLowerCase();
        Team team = hasTeam(nickname) ? getTeam(nickname) : scoreboard.registerNewTeam(nickname);
        String finalTag = getTagFromConfig("default");
        for (String tag : getTags())
            if (player.hasPermission("tabstag." + tag) && !tag.equalsIgnoreCase("default")) {
                finalTag = getTagFromConfig(tag);
                break;
            }
        PlayerSetTagEvent playerSetTagEvent = new PlayerSetTagEvent(player, finalTag, team);
        tabsTag.getServer().getPluginManager().callEvent(playerSetTagEvent);
        String tag = playerSetTagEvent.getTag();
        if (tag.length() > 16)
            tag = tag.substring(0, 15);
        playerSetTagEvent.getTeam().setPrefix(tag);
        playerSetTagEvent.getTeam().addPlayer(player);
    }

    /**
     * Get tag from configuration
     *
     * @param tag Tag in configuration file
     * @return The prefix from player
     */
    public String getTagFromConfig(String tag) {
        return getString("Tags." + tag);
    }

    private String getString(String path) {
        return tabsTag.getConfig().getString(path).replaceAll("&", "§");
    }

    /**
     * Remove team from player
     *
     * @param player Remove team from player
     */
    public void removeTeam(Player player) {
        String nickname = player.getName().toLowerCase();
        if (!hasTeam(nickname))
            return;
        Team team = getTeam(nickname);
        PlayerRemoveTagEvent playerRemoveTagEvent = new PlayerRemoveTagEvent(player, team.getPrefix(), team);
        tabsTag.getServer().getPluginManager().callEvent(playerRemoveTagEvent);
        if (playerRemoveTagEvent.isCancelled())
            return;
        playerRemoveTagEvent.getTeam().unregister();
    }

    /**
     * Remove all tags and put new tags to players
     */
    public void reload() {
        for (Player player : tabsTag.getServer().getOnlinePlayers()) {
            removeTeam(player);
            updateTeam(player);
        }
    }

    private void setHealth() {
        if (scoreboard.getObjective("health") != null)
            scoreboard.getObjective("health").unregister();
        Objective objective = scoreboard.registerNewObjective("health", "health");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName(ChatColor.RED + "❤");
    }
}
