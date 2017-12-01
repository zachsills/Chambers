package me.hulipvp.chambers.scoreboard.provider;

import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.scoreboard.ScoreboardProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyProvider implements ScoreboardProvider {

	@Override
	public String getTitle(Player player) {
		return null;
	}

	@Override
	public List<String> getLines(Player player) {
		List<String> lines = new ArrayList<>();

		lines.add("&6Game State&7: &rLobby");
		lines.add("&6Map&7: &a" + plugin.getGameManager().getGame().getMapName());
		Profile profile = plugin.getProfileManager().getProfileByUuid(player.getUniqueId());
		if (profile != null && profile.getTeam() != null) {
			lines.add("&6Team&7: " + profile.getTeam().getFormattedName());
			lines.add("&6Members&7:");
			int count = 1;
			for (UUID uuid : profile.getTeam().getMembers()) {
				lines.add(" &7" + count + ". &r" + Bukkit.getPlayer(uuid).getName());
				++count;
			}
		} else {
			lines.add("&6Team&7: &7Please choose...");
		}
		if (plugin.getGameManager().getGame().getStatus() == GameStatus.STARTING) {
			lines.add(" ");
			lines.add("&6Starting in&7:&r " + plugin.getGameManager().getGame().getCountdownTime());
		}

		return lines;
	}

}
