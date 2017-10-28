package me.hulipvp.chambers.game.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameStatus {

	LOBBY(0, "Lobby"), 
	STARTING(1, "Starting"), 
	INGAME(2, "Ingame"), 
	OVER(3, "Over");

	private int id;
	private String motd;

}
