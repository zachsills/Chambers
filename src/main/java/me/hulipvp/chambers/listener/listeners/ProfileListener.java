package me.hulipvp.chambers.listener.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.profile.structure.ProfileStatus;

public class ProfileListener implements Listener {

	private Chambers plugin = Chambers.getInstance();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Profile profile = null;
		if (plugin.getGameManager().getGame().hasStarted()) {
			profile = new Profile(player, ProfileStatus.SPECTATING);
			plugin.getGameManager().tryStart();
			event.setJoinMessage(null);
		} else {
			profile = new Profile(player, ProfileStatus.PLAYING);
			event.setJoinMessage(player.getName() + ChatColor.YELLOW + " has joined." + ChatColor.RED + "(" + Math.abs(20 - Bukkit.getOnlinePlayers().length) + "/20)");
		}
		if (profile != null) {
			plugin.getProfileManager().addProfile(profile);
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		removeProfile(event);
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent event) {
		removeProfile(event);
		event.setLeaveMessage(null);
	}
	
	public void removeProfile(PlayerEvent event) {
		Player player = event.getPlayer();
		Profile profile = plugin.getProfileManager().getProfileByUuid(player.getUniqueId());
		if (profile != null && profile.getTeam() != null) {
			plugin.getGameManager().getGame().getOffline().add(player.getName());
			profile.getTeam().getOffline().add(player.getName());
		}
		plugin.getProfileManager().removeProfile(profile);
	}
	
}
