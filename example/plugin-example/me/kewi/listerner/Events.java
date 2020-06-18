package me.kewi.listerner;

import me.kewi.ClanTag;
import me.kewi.api.PlayerSetTagEvent;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Events implements Listener {

    private final SimpleClans clan;
    private final String tag;

    public Events(ClanTag clanTag) {
        this.clan = clanTag.getSimpleClans();
        // Instance tag to get format in config.yml
        this.tag = clanTag.getConfig().getString("Format").replaceAll("&", "§");
    }

    private ClanPlayer getClan(String nickname) {
        return clan.getClanManager().getClanPlayer(nickname);
    }

    /**
     * Get tag from config
     *
     * @param nickname get clan from SimpleClans by nickname
     * @param tag      get format tag to set in team
     * @return Tag clan formatted
     */
    public String getNewTag(String nickname, String tag) {
        return tag.replace("{clan}", getClan(nickname) != null ? getClan(nickname).getClan().getColorTag() : "");
    }

    /**
     * Set tag event
     *
     * @param event update new tag to player
     */
    @EventHandler
    private void onSetTag(PlayerSetTagEvent event) {
        // get tag
        String tag = getNewTag(event.getPlayer().getName(), this.tag);
        // remove color to check is empty
        String removedColor = removeColor(tag);
        if (removedColor.charAt(0) == '[' && removedColor.charAt(1) == ']')
            return;
        // set clan tag
        event.setTag(tag);
    }

    private String removeColor(String tag) {
        tag = tag.toLowerCase();
        String out = tag.replaceAll("[&][0-9a-fk-or]", "");
        out = out.replaceAll(String.valueOf((char) 194), "");
        return out.replaceAll("[\u00a7][0-9a-fk-or]", "");
    }
}
