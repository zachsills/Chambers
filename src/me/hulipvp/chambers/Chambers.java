package me.hulipvp.chambers;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.hulipvp.chambers.claim.ClaimManager;
import me.hulipvp.chambers.game.GameManager;
import me.hulipvp.chambers.profile.ProfileManager;
import me.hulipvp.chambers.team.TeamManager;

@Getter
public class Chambers extends JavaPlugin {
	
	/* SINGLE, like me, plugin instance */
	@Getter
	private static Chambers instance;
	
	/* Game Managers */
	private ClaimManager claimManager;
	private GameManager gameManager;
	private ProfileManager profileManager;
	private TeamManager teamManager;
	
	public void onEnable() {
		
		instance = this;
		
		claimManager = new ClaimManager();
		gameManager = new GameManager();
		profileManager = new ProfileManager();
		teamManager = new TeamManager();
		
	}

}
