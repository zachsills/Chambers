package me.hulipvp.chambers.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.util.MathUtil;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class PayCommand extends ChambersCommand {

	@Command(name = "pay", description = "Send a player some money", aliases = { "sendmoney", "givemoney" }, usage = "/<command>", playerOnly = true, requiresTeam = true)
	public void onCommand(CommandArgs commandArgs) {
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		if (commandArgs.length() != 2) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <player> <amount>");
			return;
		}
		Player target = Bukkit.getPlayer(commandArgs.getArgs(0));
		if (target == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "That player is not online.");
			return;
		}
		Profile targetProfile = plugin.getProfileManager().getProfileByUuid(target.getUniqueId());
		if (targetProfile.getTeam() == null || profile.getTeam() != targetProfile.getTeam()) {
			profile.sendMessage(ChatColor.RED + "That player is not on your team!");
			return;
		}
		if (!MathUtil.isInt(commandArgs.getArgs(1))) {
			profile.sendMessage(ChatColor.RED + "Please enter a valid amount.");
			return;
		}
		int amount = Integer.valueOf(commandArgs.getArgs(1));
		profile.withdraw(amount);
		targetProfile.deposit(amount);
		profile.sendMessage(ChatColor.YELLOW + "You have sent " + ChatColor.RED + "$" + amount + ChatColor.YELLOW + " to " + ChatColor.GREEN + commandArgs.getArgs(1) + ChatColor.YELLOW + ".");
		targetProfile.sendMessage(ChatColor.YELLOW + "You have recieved " + ChatColor.GREEN + "$" + amount + ChatColor.YELLOW + " from " + ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + ".");
	}

}
