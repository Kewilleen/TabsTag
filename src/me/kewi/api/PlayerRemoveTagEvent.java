package me.kewi.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

/**
 * <h1>Remove player team</h1>
 *
 * <p>When player quit or is kicked, this is called to remove the player team. If player re-join in server, PlayerSetTagEvent is called to set new team to player.</p>
 */
public class PlayerRemoveTagEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private Team team;

    public PlayerRemoveTagEvent(Player player, Team team) {
        this.player = player;
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
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
