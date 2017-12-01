package me.hulipvp.chambers.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.profile.structure.ProfileStatus;
import net.md_5.bungee.api.ChatColor;

@AllArgsConstructor
@Getter
public class RespawnTask extends BukkitRunnable {
	
	private Profile profile;
	private Player player;
	private Location startingLocation;

	@Override
	public void run() {
		if (profile == null || player == null || !player.isOnline()) {
			cancel();
			return;
		}
		if (profile.getTeam().isRaidable()) {
			player.sendMessage(ChatColor.RED + "Your team has gone raidable and you will not respawn.");
			cancel();
			return;
		}
		if (startingLocation.distance(player.getLocation()) > 40) {
			player.teleport(startingLocation);
			player.sendMessage(ChatColor.RED + "You cannot move more than 40 blocks from where you died.");
		}
		if (profile.getRespawnTime() > 0) {
			profile.setRespawnTime(profile.getRespawnTime() - 1);
		} else {
			profile.setProfileStatus(ProfileStatus.PLAYING);
			player.spigot().respawn();
			player.teleport(profile.getTeam().getHome());
			Bukkit.getOnlinePlayers().forEach(other -> other.showPlayer(player));
			Bukkit.getServer().getPluginManager().callEvent(new PlayerRespawnEvent(player, profile.getTeam().getHome(), false));
		}
	}
	
}
