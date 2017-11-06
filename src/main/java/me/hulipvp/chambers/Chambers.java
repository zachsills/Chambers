package me.hulipvp.chambers;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.hulipvp.chambers.claim.ClaimManager;
import me.hulipvp.chambers.command.BalanceCommand;
import me.hulipvp.chambers.command.GameCommand;
import me.hulipvp.chambers.command.PayCommand;
import me.hulipvp.chambers.command.ToggleBoardCommand;
import me.hulipvp.chambers.command.VillagerCommand;
import me.hulipvp.chambers.config.DataFile;
import me.hulipvp.chambers.game.GameManager;
import me.hulipvp.chambers.listener.ListenerManager;
import me.hulipvp.chambers.profile.ProfileManager;
import me.hulipvp.chambers.scoreboard.ScoreboardWrapper;
import me.hulipvp.chambers.scoreboard.provider.ProviderResolver;
import me.hulipvp.chambers.shop.VillagerManager;
import me.hulipvp.chambers.team.TeamManager;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.team.command.admin.TeamBypassCommand;
import me.hulipvp.chambers.team.command.admin.TeamClaimCommand;
import me.hulipvp.chambers.team.command.admin.TeamForceJoinCommand;
import me.hulipvp.chambers.team.command.admin.TeamForceLeaveCommand;
import me.hulipvp.chambers.team.command.admin.TeamForcePlaceCommand;
import me.hulipvp.chambers.team.command.admin.TeamSetDtrCommand;
import me.hulipvp.chambers.team.command.admin.TeamSetHomeCommand;
import me.hulipvp.chambers.team.command.admin.TeamSetVillagerCommand;
import me.hulipvp.chambers.team.command.normal.TeamChatCommand;
import me.hulipvp.chambers.team.command.normal.TeamHelpCommand;
import me.hulipvp.chambers.team.command.normal.TeamLocationCommand;
import me.hulipvp.chambers.team.command.normal.TeamShowCommand;
import me.hulipvp.chambers.util.commandapi.CommandFramework;

/**
 * Copyright (c) 2017 Zach Sills
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
	private ListenerManager listenerManager;
	private VillagerManager villagerManager;

	/* Scoreboard */
	private ScoreboardWrapper scoreboardWrapper;

	/* Commands & Listeners */
	private CommandFramework commandFramework;

	/* Data Files */
	private DataFile dataFile;

	public void onEnable() {

		instance = this;

		dataFile = new DataFile(this, "data");
		
		claimManager = new ClaimManager();
		gameManager = new GameManager();
		profileManager = new ProfileManager();
		teamManager = new TeamManager();
		listenerManager = new ListenerManager();
		villagerManager = new VillagerManager();

		scoreboardWrapper = new ScoreboardWrapper(this, new ProviderResolver());

		commandFramework = new CommandFramework(this);

		registerAllCommands();
		
	}
	
	public void onDisable() {
		gameManager.clearAllMobs();
	}
	
	private void registerAllCommands() {
		new BalanceCommand();
		new GameCommand();
		new PayCommand();
		new ToggleBoardCommand();
		new VillagerCommand();
		new TeamCommand();
		new TeamBypassCommand();
		new TeamClaimCommand();
		new TeamForceJoinCommand();
		new TeamForceLeaveCommand();
		new TeamForcePlaceCommand();
		new TeamSetDtrCommand();
		new TeamSetHomeCommand();
		new TeamSetVillagerCommand();
		new TeamChatCommand();
		new TeamHelpCommand();
		new TeamLocationCommand();
		new TeamShowCommand();
	}
	
}
