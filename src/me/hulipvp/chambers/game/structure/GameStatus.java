package me.hulipvp.chambers.game.structure;

import lombok.Getter;

@Getter
public enum GameStatus {
	
	LOBBY(0, "Lobby"),
	STARTING(1, "Starting"),
	INGAME(2, "Ingame"),
	OVER(3, "Over");
	
	private int id;
	private String motd;
	
	GameStatus(int id, String motd) {
		this.id = id;
		this.motd = motd;
	}

}
