package me.hulipvp.chambers.listener.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.profile.structure.ProfileStatus;

public class ProfileStatusListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getProfileStatus() != ProfileStatus.PLAYING) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getProfileStatus() != ProfileStatus.PLAYING) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getProfileStatus() != ProfileStatus.PLAYING) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getProfileStatus() != ProfileStatus.PLAYING) {
			event.setCancelled(true);
		}
	}
	
}
