package me.kewi.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

/**
 * <h1>Represents a tag of the team in which the player participates</h1>
 *
 * <p>Once the player has his tag, just update the tag's content with the same team,
 * so there is no need to unregister and register teams.</p>
 */
public class PlayerSetTagEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Player player;
    private String tag;
    private Team team;

    public PlayerSetTagEvent(Player player, String tag, Team team) {
        this.player = player;
        this.tag = tag;
        this.team = team;
    }

    /**
     * Get player
     *
     * @return Obtain the current player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get current tag
     *
     * @return Full tag of this Player
     */
    public String getTag() {
        return tag;
    }

    /**
     * Give a new tag
     *
     * @param tag Tag to give this Player
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Get the team
     *
     * @return Team from Player
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Set an team
     *
     * @param team Sets the Team of this Player
     */
    public void setTeam(Team team) {
        this.team = team;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
