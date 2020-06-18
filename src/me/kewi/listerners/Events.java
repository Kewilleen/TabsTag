package me.kewi.listerners;

import me.kewi.TabsTag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    private final TabsTag tabsTag;

    public Events(TabsTag tabsTag) {
        this.tabsTag = tabsTag;
    }

    @EventHandler
    private void onJoinEvent(PlayerJoinEvent event) {
        tabsTag.getTagManager().updateTeam(event.getPlayer());
    }

    @EventHandler
    private void onQuitEvent(PlayerQuitEvent event) {
        tabsTag.getTagManager().removeTeam(event.getPlayer());
    }

    @EventHandler
    private void onKickEvent(PlayerKickEvent event) {
        tabsTag.getTagManager().removeTeam(event.getPlayer());
    }


}
