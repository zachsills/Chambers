package me.hulipvp.chambers.team.command;

import org.bukkit.entity.Player;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamCommand {
	
	public Chambers plugin = Chambers.getInstance();
	
	public TeamCommand() {
		plugin.getCommandFramework().registerCommands(this);
		plugin.getCommandFramework().registerHelp();
	}
	
	@Command(name = "faction", description = "The main command for Teams", aliases = { "f", "t", "s", "team", "squad" }, usage = "/<command> <args>")
	public void onCommand(CommandArgs commandArgs) {
		Player player = (Player) commandArgs.getSender();
		showHelp(player, 1);
	}
	
	public void showHelp(Player player, int page) {
		
	}

}
