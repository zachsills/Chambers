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
import me.hulipvp.chambers.game.structure.Game;
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
			event.setJoinMessage(null);
		} else {
			profile = new Profile(player, ProfileStatus.PLAYING);
			plugin.getGameManager().tryStart();
			event.setJoinMessage(player.getName() + ChatColor.YELLOW + " has joined. " + ChatColor.RED + "(" + Bukkit.getOnlinePlayers().size() + "/20)");
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
		Game game = plugin.getGameManager().getGame();
		Profile profile = plugin.getProfileManager().getProfileByUuid(player.getUniqueId());
		if (profile != null && profile.getTeam() != null) {
			if (game.hasStarted()) {
				plugin.getGameManager().getGame().getOffline().add(player.getName());
				profile.getTeam().getOffline().add(player.getName());
			}
			profile.getTeam().removeMember(profile);
		}
		plugin.getProfileManager().removeProfile(profile);
	}

}
