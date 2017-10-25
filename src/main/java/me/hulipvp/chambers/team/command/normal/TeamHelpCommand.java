package me.hulipvp.chambers.team.command.normal;

import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamHelpCommand extends TeamCommand {
	
	@Command(name = "faction.help", description = "The main command for Teams", aliases = { "f.help", "t.help", "s.help", "team.help", "squad.help" }, usage = "/<command> <args>", requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		showHelp(commandArgs.getPlayer());
	}

}
