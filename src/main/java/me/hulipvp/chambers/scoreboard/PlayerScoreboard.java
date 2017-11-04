package me.hulipvp.chambers.scoreboard;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.TeamType;

final class PlayerScoreboard {

	final Player player;
	final Objective objective;
	final Scoreboard scoreboard;
	final Team red, blue, green, yellow;
	final Map<TeamType, Team> teams;

	PlayerScoreboard(Player player) {
		this.player = player;
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.objective = scoreboard.getObjective("SidebarWrapper") == null ? scoreboard.registerNewObjective("SidebarWrapper", "dummy") : scoreboard.getObjective("SidebarWrapper");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.objective.setDisplayName("");
		if (player.getScoreboard() != scoreboard) {
			player.setScoreboard(scoreboard);
		}
		update();
		this.teams = new HashMap<>();
		this.red = scoreboard.registerNewTeam("Red");
		this.blue = scoreboard.registerNewTeam("Blue");
		this.green = scoreboard.registerNewTeam("Green");
		this.yellow = scoreboard.registerNewTeam("Yellow");
		Arrays.stream(TeamType.values()).filter(teamType -> teamType.isPlayerTeam()).forEach(teamType -> {
			Team team = scoreboard.getTeam(teamType.getName());
			team.setPrefix(teamType.getColor().toString());
			team.setAllowFriendlyFire(false);
			team.setCanSeeFriendlyInvisibles(true);
			this.teams.put(teamType, team);
		});
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
	
	void updateTeams(Player update) {
		updateTeams(Collections.singleton(update));
	}
	
	void updateTeams(Iterable<? extends Player> updates) {
		for (Player player : updates) {
			Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(player.getUniqueId());
			if (profile.getTeam() == null) {
				this.teams.forEach((teamType, team) -> {
					if (team.hasPlayer(player)) {
						team.removePlayer(player);
					}
				});
				continue;
			}
			
			this.teams.forEach((teamType, team) -> {
				if (profile.getTeam().getType() != teamType) {
					if (team.hasPlayer(player)) {
						team.removePlayer(player);
					}
				} else {
					if (!team.hasPlayer(player)) {
						team.addPlayer(player);
					}
				}
			});
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