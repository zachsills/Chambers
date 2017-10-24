package me.hulipvp.chambers.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.scoreboard.ScoreboardProvider;
import me.hulipvp.chambers.util.Color;

public class LobbyProvider implements ScoreboardProvider {

	@Override
	public String getTitle(Player player) {
		return null;
	}

	@Override
	public List<String> getLines(Player player) {

		List<String> lines = new ArrayList<>();

		lines.add(Color.color("&6Game State&7: &rLobby"));
		lines.add(Color.color("&6Map&7: &aClassic"));
		Profile profile = plugin.getProfileManager().getProfileByUuid(player.getUniqueId());
		if (profile != null && profile.getTeam() != null) {
			lines.add(Color.color("&6&lTeam: " + profile.getTeam().getFormattedName()));
			lines.add(Color.color("&6&lMembers:"));
			int count = 1;
			for (UUID uuid : profile.getTeam().getMembers()) {
				lines.add(Color.color(" &7" + count + ". &r" + Bukkit.getPlayer(uuid).getName()));
				++count;
			}
		}
		if (plugin.getGameManager().getGame().getStatus() == GameStatus.STARTING) {
			lines.add(" ");
			lines.add(Color.color("&6Countdown&7:&r " + 60));
		}

		return lines;
	}

}
