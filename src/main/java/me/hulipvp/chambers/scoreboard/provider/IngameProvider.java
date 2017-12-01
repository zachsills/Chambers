package me.hulipvp.chambers.scoreboard.provider;

import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.profile.structure.ProfileStatus;
import me.hulipvp.chambers.scoreboard.ScoreboardProvider;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class IngameProvider implements ScoreboardProvider {

	@Override
	public String getTitle(Player player) {
		return null;
	}

	@Override
	public List<String> getLines(Player player) {
		List<String> lines = new ArrayList<>();

		Profile profile = plugin.getProfileManager().getProfileByUuid(player.getUniqueId());
		switch (profile.getProfileStatus()) {
		case SPECTATING: {
			lines.add("&7You are a spectator.");
			break;
		}
		case RESPAWNING: {
			lines.add("&7You are respawning.");
			lines.add("&6&lRespawning in:&7 " + profile.getRespawnTime());
			break;
		}
		case PLAYING: {
			lines.add("&e&lTeam:&r " + profile.getTeam().getFormattedName());
			lines.add("&4&lDTR:&c " + profile.getTeam().getDtr());
			lines.add("&a&lBalance:&c $" + profile.getBalance());
			if (plugin.getGameManager().getGame().getInvincibilityTime() > 0) {
				lines.add("&9&lInvincibility:&c " + formatIntToTime(plugin.getGameManager().getGame().getInvincibilityTime()));
			}
			if (EnderpearlListener.hasCooldown(player)) {
				lines.add("&5Enderpearl:&c " + String.valueOf(new DecimalFormat("0.0").format(EnderpearlListener.getMillisecondsLeft(player) / 1000.0)));
			}
			break;
		}
		}
		lines.add(profile.getProfileStatus() == ProfileStatus.PLAYING ? 0 : 1, "&6&lGame Time:&c " + formatIntToTime(plugin.getGameManager().getGame().getTotalTime()));
		if (plugin.getKothManager().getKoth() != null) {
			lines.add("&9&l" + plugin.getKothManager().getKoth().getName() + ":&c " + formatIntToTime(plugin.getKothManager().getKoth().getTime()));
		}

		return lines;
	}

}
