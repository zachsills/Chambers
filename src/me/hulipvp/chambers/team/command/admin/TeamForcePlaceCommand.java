package me.hulipvp.chambers.team.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamForcePlaceCommand extends TeamCommand {
	
	@Command(name = "faction.forceplace", description = "Forcefully place a player onto a Team", aliases = { "f.forceplace", "t.forceplace", "s.forceplace", "team.forceplace", "squad.forceplace" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		if (commandArgs.length() != 2) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <player> <team>");
			return;
		}
		String targetName = commandArgs.getArgs(0);
		Player target = Bukkit.getPlayer(targetName);
		if (target == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "A player by the name " + targetName + " was not found.");
			return;
		}
		Team team = plugin.getTeamManager().getTeamByString(commandArgs.getArgs(1));
		if (team == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "That team is invalid.");
			return;
		}
		Profile profile = plugin.getProfileManager().getProfileByUuid(target.getUniqueId());
		team.addMember(profile);
		profile.sendMessage(ChatColor.GRAY + "You have forcecully placed  " +  ChatColor.DARK_AQUA + targetName + " on the team " + team.getFormattedName() + ChatColor.GRAY + ".");
	}

}
