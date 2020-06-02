package me.kewi.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * When remove a tag from Player
 *
 * This class show the player and your prefix
 */
public class PlayerRemoveTagEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();
    private final Player player;
    private final Scoreboard scoreboard;
    private Team team;
    private String prefix;
    private boolean cancelled;

    public PlayerRemoveTagEvent(Player player, Scoreboard scoreboard, Team team, String prefix) {
        this.player = player;
        this.scoreboard = scoreboard;
        this.team = team;
        this.prefix = prefix;
        this.cancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     *
     * Get player removed
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Main Scoreboard
     *
     * Get main scoreboard
     *
     * @return Scoreboard
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     *
     * Team
     *
     * Get team tag
     *
     * @return Team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Set a tag team
     *
     * Setting a tag team
     *
     * @param team new Team
     */
    public void setTeam(Team team) {
        this.team = team;
    }


    /**
     * Get tag
     *
     * Getting a prefix from player
     *
     * @return string
     */
    public String getPrefix() {
        return prefix;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
