package me.hulipvp.chambers.scoreboard.provider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.profile.structure.ProfileStatus;
import me.hulipvp.chambers.scoreboard.ScoreboardProvider;
import me.hulipvp.chambers.util.Color;

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
			lines.add(Color.color("&7You are a spectator."));
			break;
		}
		case RESPAWNING: {
			lines.add(Color.color("&7You are respawning."));
			lines.add(Color.color("&6&lRespawning in:&7 " + profile.getRespawnTime()));
			break;
		}
		case PLAYING: {
			lines.add(Color.color("&e&lTeam:&r " + profile.getTeam().getFormattedName()));
			lines.add(Color.color("&4&lDTR:&c " + profile.getTeam().getDtr()));
			lines.add(Color.color("&a&lBalance:&c $" + profile.getBalance()));
			if (plugin.getGameManager().getGame().getInvincibilityTime() > 0) {
				lines.add(Color.color("&9&lInvincibility:&c " + formatIntToTime(plugin.getGameManager().getGame().getInvincibilityTime())));
			}
			if (EnderpearlListener.hasCooldown(player)) {
				lines.add(Color.color("&5Enderpearl:&c " + String.valueOf(new DecimalFormat("0.0").format(EnderpearlListener.getMillisecondsLeft(player) / 1000.0))));
			}
			break;
		}
		}
		lines.add(profile.getProfileStatus() == ProfileStatus.PLAYING ? 0 : 1, Color.color("&6&lGame Time:&c " + formatIntToTime(plugin.getGameManager().getGame().getTotalTime())));
		if (plugin.getKothManager().getKoth() != null) {
			lines.add(Color.color("&9&l" + plugin.getKothManager().getKoth().getName() + ":&c " + formatIntToTime(plugin.getKothManager().getKoth().getTime())));
		}

		return lines;
	}

}
