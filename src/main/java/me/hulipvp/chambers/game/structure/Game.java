package me.hulipvp.chambers.game.structure;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.LocationUtil;

@Getter
@Setter
public class Game {

	private GameStatus status;
	private int totalTime, countdownTime, invincibilityTime;
	private Set<String> offline;
	private Team winner;
	private String mapName;
	private Location spawnLocation;

	public Game(GameStatus status) {
		this.status = status;
		this.totalTime = 0;
		this.countdownTime = 45;
		this.invincibilityTime = 60;
		this.offline = new HashSet<>();
		this.winner = null;
		this.mapName = Chambers.getInstance().getDataFile().getString("MAP_NAME");
		this.spawnLocation = Chambers.getInstance().getDataFile().getString("SPAWN_LOCATION") == null ? Bukkit.getWorlds().get(0).getSpawnLocation() : LocationUtil.deserializeLocation(Chambers.getInstance().getDataFile().getString("SPAWN_LOCATION"));
	}

	public boolean hasStarted() {
		return this.status == GameStatus.STARTING || this.status == GameStatus.INGAME;
	}

}
