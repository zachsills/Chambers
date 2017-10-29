package me.hulipvp.chambers.claim.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;

public class ClaimProfileListener implements Listener {
	
	public void removeProfile(PlayerEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (Chambers.getInstance().getClaimManager().isClaiming(profile)) {
			Chambers.getInstance().getClaimManager().removeClaimProfile(profile);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		removeProfile(event);
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent event) {
		removeProfile(event);
	}

}
