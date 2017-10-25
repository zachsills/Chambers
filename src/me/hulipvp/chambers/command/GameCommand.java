package me.hulipvp.chambers.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class GameCommand extends ChambersCommand {
	
	@Command(name = "game", description = "The main to manage the Game", aliases = { "managegame" }, usage = "/<command> <args>", requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		Game game = plugin.getGameManager().getGame();
		if (game == null) {
			commandArgs.getSender().sendMessage(ChatColor.RED + "There is no game to manage. Please restart this server as an error had occured while enabling the plugin.");
			return;
		}
		String arg = commandArgs.getArgs(0);
		if (arg.equalsIgnoreCase("start")) {
			if (game.hasStarted()) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has already started.");
				return;
			}
			plugin.getGameManager().start();
			Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has force started the game.");
		} else if (arg.equalsIgnoreCase("stop")) {
			if (!game.hasStarted()) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has not started yet.");
				return;
			}
			plugin.getGameManager().stop();;
			Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has force stopped the game.");
		}
	}

}
