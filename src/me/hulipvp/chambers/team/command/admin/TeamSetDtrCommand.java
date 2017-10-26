package me.hulipvp.chambers.team.command.admin;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.MathUtil;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamSetDtrCommand extends TeamCommand {
	
	@Command(name = "faction.setdtr", description = "Set the DTR for a Team", aliases = { "f.setdtr", "t.setdtr", "s.setdtr", "team.setdtr", "squad.setdtr" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		if (commandArgs.length() != 1) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <team> <amount>");
			return;
		}
		Team team = plugin.getTeamManager().getTeamByString(commandArgs.getArgs(0));
		if (team == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "That team is invalid.");
			return;
		}
		if (!MathUtil.isInt(commandArgs.getArgs(1))) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Please enter a valid amount.");
			return;
		}
		double dtr = Double.parseDouble(commandArgs.getArgs(1));
		if (Math.abs(dtr) > 6) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Please enter an amount that 6 away from zero.");
			return;
		}
		team.setDtr(dtr);
		commandArgs.getPlayer().sendMessage(ChatColor.GRAY + "Set the DTR for " + team.getFormattedName() + ChatColor.GRAY + " to " + ChatColor.AQUA + dtr + ChatColor.GRAY + ".");
	}

}
