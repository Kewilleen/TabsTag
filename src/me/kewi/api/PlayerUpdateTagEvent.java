package me.kewi.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 *
 * Update tag player event
 *
 * When player join in event, the plugin call a event handler to set
 * the new tag in head and tablist from player, this part you will see
 * how call our system to modify your tags
 *
 */
public class PlayerUpdateTagEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();
    private final Player player;
    private final Scoreboard scoreboard;
    private Team team;
    private String tag;
    private boolean cancelled;

    public PlayerUpdateTagEvent(Player player, Scoreboard scoreboard, Team team, String tag) {
        this.player = player;
        this.scoreboard = scoreboard;
        this.team = team;
        this.tag = tag;
        this.cancelled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    /**
     *
     * Get player
     *
     * Get player to setting the tag
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * Get main scoreboard
     *
     * Get a main scoreboard from bukkit
     *
     * @return Scoreboard
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     *
     * Get a player team
     *
     * Getting a default player team
     *
     * @return Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     *
     * Getting a player tag
     *
     * When he is joing in the server, call this plugin to take the prefix
     *
     * @return String
     */
    public String getTag() {
        return tag;
    }

    /**
     * Change the tag
     *
     * Setting a new tag if you need make a tag to player
     *
     * @param tag set a new tag
     */
    public void setTag(String tag){
        this.tag = tag;
    }


    /**
     * Setting the team
     *
     * Don't like my default team? Okay, you can set your.
     *
     * @param team needed a team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
