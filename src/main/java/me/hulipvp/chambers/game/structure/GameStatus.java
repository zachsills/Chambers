package me.hulipvp.chambers.game.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameStatus {

	LOBBY("Lobby"), 
	STARTING("Starting"), 
	INGAME("Ingame"), 
	OVER("Over");

	private String motd;

}
