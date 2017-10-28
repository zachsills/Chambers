package me.hulipvp.chambers.game.structure;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.team.structure.Team;

@Getter
@Setter
public class Game {

	private GameStatus status;
	private int totalTime, countdownTime, invincibilityTime;
	private Set<String> offline;
	private Team winner;

	public Game(GameStatus status) {
		this.status = status;
		this.totalTime = 0;
		this.countdownTime = 60;
		this.invincibilityTime = 60;
		this.offline = new HashSet<>();
		this.winner = null;
	}

	public boolean hasStarted() {
		return this.status == GameStatus.STARTING || this.status == GameStatus.INGAME;
	}

}
