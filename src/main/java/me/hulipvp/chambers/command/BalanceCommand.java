package me.hulipvp.chambers.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class BalanceCommand extends ChambersCommand {
	
	@Command(name = "balance", description = "See how much money a player has", aliases = { "bal", "checkbalance" }, usage = "/<command> <args>", playerOnly = true, requiresTeam = true)
	public void onCommand(CommandArgs commandArgs) {
		if (commandArgs.length() == 0) {
			Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
			commandArgs.getPlayer().sendMessage(ChatColor.YELLOW + "Balance: " + ChatColor.GREEN + profile.getBalance());
			return;
		}
		Player target = Bukkit.getPlayer(commandArgs.getArgs(0));
		if (target == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "That player is not online.");
			return;
		}
		Profile profile = plugin.getProfileManager().getProfileByUuid(target.getUniqueId());
		commandArgs.getPlayer().sendMessage(ChatColor.YELLOW + target.getName() + "'s Balance: " + ChatColor.GREEN + profile.getBalance());
	}

}
