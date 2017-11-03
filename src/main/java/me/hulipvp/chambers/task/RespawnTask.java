package me.hulipvp.chambers.task;

import org.bukkit.Bukkit;
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

	@Override
	public void run() {
		if (this.profile == null || this.player == null || !this.player.isOnline()) {
			this.cancel();
			return;
		}
		if (this.profile.getTeam().isRaidable()) {
			this.player.sendMessage(ChatColor.RED + "Your team has gone raidable and you will not respawn.");
			this.cancel();
			return;
		}
		if (this.profile.getRespawnTime() > 0) {
			this.profile.setRespawnTime(this.profile.getRespawnTime() - 1);
		} else {
			this.profile.setProfileStatus(ProfileStatus.PLAYING);
			this.player.spigot().respawn();
			this.player.teleport(this.profile.getTeam().getHome());
			Bukkit.getOnlinePlayers().forEach(other -> other.showPlayer(this.player));
			Bukkit.getServer().getPluginManager().callEvent(new PlayerRespawnEvent(this.player, this.profile.getTeam().getHome(), false));
		}
	}
	
}
