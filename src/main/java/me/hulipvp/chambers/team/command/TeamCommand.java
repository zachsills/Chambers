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
	
	@Command(name = "faction", description = "The main command for Teams", aliases = { "f", "t", "s", "team", "squad" }, usage = "/<command> <args>", requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		Player player = (Player) commandArgs.getSender();
		showHelp(player);
	}
	
	public void showHelp(Player player) {
		player.sendMessage("&6&m-------------------------------------------------------------");
	    player.sendMessage("&d&lFaction Help&7 - &fInformation on how to use faction commands");
	    player.sendMessage("&6&m-------------------------------------------------------------");
	    player.sendMessage("&9General Commands:");
	    player.sendMessage("&e/f home &7- Teleport to your faction home");
	    player.sendMessage("&e/f chat <type> &7- Displays this help menu");
	    player.sendMessage(" ");
	    player.sendMessage("&9Information Commands:");
	    player.sendMessage("&e/f help &7- Displays this help menu");
	    player.sendMessage("&e/f show [player|teamName]&7 - Display team information");
	    player.sendMessage(" ");
	    player.sendMessage("&9Chat Usage:");
	    player.sendMessage("&e!<message> &7- Message in all chat");
	    player.sendMessage("&e@<message> &7- Message in faction chat");
	    player.sendMessage("&e/tl &7- Quickly send your coords to your teammates");
	    player.sendMessage("&6&m--------------------------------------------------------------");
	}

}
