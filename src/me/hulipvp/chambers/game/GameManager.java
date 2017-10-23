package me.hulipvp.chambers.game;

import lombok.Getter;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;

@Getter
public class GameManager {
	
	private Game game;
	
	public GameManager() {
		
		game = new Game(GameStatus.LOBBY);
		
	}

}
