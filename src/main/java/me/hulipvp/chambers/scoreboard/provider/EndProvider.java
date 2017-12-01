package me.hulipvp.chambers.scoreboard.provider;

import me.hulipvp.chambers.scoreboard.ScoreboardProvider;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EndProvider implements ScoreboardProvider {

	@Override
	public String getTitle(Player player) {
		return null;
	}

	@Override
	public List<String> getLines(Player player) {
		List<String> lines = new ArrayList<>();

		lines.add("&6&lMap:&7 " + plugin.getGameManager().getGame().getMapName());
		lines.add("&6&lWinner:&r " + (plugin.getGameManager().getGame().getWinner() == null ? "Forced Win" : plugin.getGameManager().getGame().getWinner().getFormattedName()));

		return lines;
	}

}
