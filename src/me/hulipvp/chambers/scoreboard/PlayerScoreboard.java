package me.hulipvp.chambers.scoreboard;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

final class PlayerScoreboard {

	final Player player;
	final Objective objective;
	final Scoreboard scoreboard;

	PlayerScoreboard(Player player) {
		this.player = player;
		this.scoreboard = player.getScoreboard() == Bukkit.getScoreboardManager()
				? Bukkit.getScoreboardManager().getNewScoreboard() : player.getScoreboard();
		this.objective = scoreboard.getObjective("SidebarWrapper") == null
				? scoreboard.registerNewObjective("SidebarWrapper", "dummy")
				: scoreboard.getObjective("SidebarWrapper");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.objective.setDisplayName("");
		if (player.getScoreboard() != scoreboard) {
			player.setScoreboard(scoreboard);
		}
		update();
	}

	void update() {
		if (!isActive()) {
			return;
		}
		ScoreboardProvider provider = ScoreboardWrapper.instance.provider;
		String title = provider.getTitle(player);
		if (!objective.getDisplayName().equals(title)) {
			objective.setDisplayName(title);
		}
		List<String> lines = provider.getLines(player);
		Collections.reverse(lines);
		int currentLine = 1;
		if (lines.size() != scoreboard.getEntries().size()) {
			scoreboard.getEntries().forEach(entry -> scoreboard.resetScores(entry));
		}
		if (currentLine > 14) {
			return;
		}
		for (String line : lines) {
			final String code = nextString(currentLine);
			String left = "", right = "";
			if (line.length() > 16) {
				left = line.substring(0, 16);
				String suffix = ChatColor.getLastColors(left) + line.substring(16, line.length());
				if (suffix.length() > 16) {
					if (suffix.length() <= 16) {
						suffix = line.substring(16, line.length());
						right = suffix.substring(0, suffix.length());
					} else {
						right = suffix.substring(0, 16);
					}
				} else {
					right = suffix;
				}
			} else {
				left = line;
				right = "";
			}
			applyTeam(code, left, right);
			objective.getScore(code).setScore(currentLine++);
		}
	}

	void applyTeam(String code, String left, String right) {
		Team team = scoreboard.getTeam(code);
		if (team == null) {
			team = scoreboard.registerNewTeam(code);
		}
		if (!team.hasEntry(code)) {
			team.addEntry(code);
		}
		team.setPrefix(left);
		team.setSuffix(right);
	}

	boolean isActive() {
		return player != null && player.isOnline() && player.getScoreboard() == scoreboard;
	}

	void disable() {
		player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		scoreboard.getEntries().forEach(entry -> scoreboard.resetScores(entry));
		scoreboard.getObjectives().forEach(obj -> obj.unregister());
		scoreboard.getTeams().forEach(team -> team.unregister());
	}

	String nextString(int line) {
		String output = "";
		for (int i = 0; i < 4; i++) {
			output = output + ChatColor.values()[Math.min(line, 15)].toString();
		}
		return output + ChatColor.WHITE;
	}

}