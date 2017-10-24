package me.hulipvp.chambers.game;

import org.bukkit.Bukkit;

import lombok.Getter;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;

@Getter
public class GameManager {
	
	private Game game;
	
	public GameManager() {
		
		game = new Game(GameStatus.LOBBY);
		
	}
	
	/**
	 * Starts the Game
	 */
	public void start() {
		
	}
	
	/**
	 * Stops the Game
	 */
	public void stop() {
		
	}

	/**
	 * Attempts to start the Game
	 */
	public void tryStart() {
		if (Bukkit.getOnlinePlayers().length == 20) {
			start();
		}
	}

}
