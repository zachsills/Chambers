package me.hulipvp.chambers.team.command.admin;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamForceJoinCommand extends TeamCommand {
	
	@Command(name = "faction.forcejoin", description = "Force join a Team", aliases = { "f.forcejoin", "t.forcejoin", "s.forcejoin", "team.forcejoin", "squad.forcejoin" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		if (commandArgs.length() != 1) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <team>");
			return;
		}
		Team team = plugin.getTeamManager().getTeamByString(commandArgs.getArgs(0));
		if (team == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "That team is invalid.");
			return;
		}
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		team.addMember(profile);
		profile.sendMessage(ChatColor.GRAY + "You have forcecully joined the team " + team.getFormattedName() + ChatColor.GRAY + ".");
	}

}
