package me.hulipvp.chambers.team.command.admin;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamForceLeaveCommand extends TeamCommand {
	
	@Command(name = "faction.forceleave", description = "Force join a Team", aliases = { "f.forceleave", "t.forceleave", "s.forceleave", "team.forceleave", "squad.forceleave" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
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
		team.removeMember(profile);
		profile.sendMessage(ChatColor.GRAY + "You have forcecully left the team " + team.getFormattedName() + ChatColor.GRAY + ".");
	}

}
