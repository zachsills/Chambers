package me.hulipvp.chambers.team.command;

import org.bukkit.entity.Player;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.util.Color;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamCommand {

	public Chambers plugin = Chambers.getInstance();

	public TeamCommand() {
		plugin.getCommandFramework().registerCommands(this);
		plugin.getCommandFramework().registerHelp();
	}

	@Command(name = "faction", description = "The main command for Teams", aliases = { "f", "t", "s", "team", "squad" }, usage = "/<command>", requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		Player player = (Player) commandArgs.getSender();
		showHelp(player);
	}

	public void showHelp(Player player) {
		player.sendMessage(Color.color("&6&m-------------------------------------------------------------"));
		player.sendMessage(Color.color("&d&lFaction Help&7 - &fInformation on how to use faction commands"));
		player.sendMessage(Color.color("&6&m-------------------------------------------------------------"));
		player.sendMessage(Color.color("&9General Commands:"));
		player.sendMessage(Color.color("&e/f home &7- Teleport to your faction home"));
		player.sendMessage(Color.color("&e/f chat <type> &7- Displays this help menu"));
		player.sendMessage(Color.color(" "));
		player.sendMessage(Color.color("&9Information Commands:"));
		player.sendMessage(Color.color("&e/f help &7- Displays this help menu"));
		player.sendMessage(Color.color("&e/f show [player|teamName]&7 - Display team information"));
		player.sendMessage(Color.color(" "));
		player.sendMessage(Color.color("&9Chat Usage:"));
		player.sendMessage(Color.color("&e!<message> &7- Message in all chat"));
		player.sendMessage(Color.color("&e@<message> &7- Message in faction chat"));
		player.sendMessage(Color.color("&e/tl &7- Quickly send your coords to your teammates"));
		player.sendMessage(Color.color("&6&m--------------------------------------------------------------"));
	}

}
