package me.hulipvp.chambers.game.structure;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
	
	private GameStatus status;
	private int totalTime, invincibilityTime;
	private Set<String> offline;
	
	
	public Game(GameStatus status) {
		this.status = status;
		this.totalTime = 0;
		this.invincibilityTime = 60;
		this.offline = new HashSet<>();
	}
	
	public boolean hasStarted() {
		return this.status == GameStatus.STARTING || this.status == GameStatus.INGAME;
	}

}
