package me.hulipvp.chambers.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.MathUtil;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class GameCommand extends ChambersCommand {
	
	@Command(name = "game", description = "The main command to manage the Game", aliases = { "managegame" }, usage = "/<command> <args>", requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		Game game = plugin.getGameManager().getGame();
		if (game == null) {
			commandArgs.getSender().sendMessage(ChatColor.RED + "There is no game to manage. Please restart this server as an error had occured while enabling the plugin.");
			return;
		}
		if (commandArgs.length() < 1) {
			commandArgs.getSender().sendMessage(ChatColor.RED + "/game <start|stop|setcountdowntime>");
			return;
		}
		String arg = commandArgs.getArgs(0);
		if (arg.equalsIgnoreCase("start")) {
			if (game.hasStarted()) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has already started.");
				return;
			}
			plugin.getGameManager().start();
			Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has forcefully started the countdown.");
		} else if (arg.equalsIgnoreCase("stop")) {
			if (!game.hasStarted()) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has not started yet.");
				return;
			}
			plugin.getGameManager().stop(null);
			Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has forcefully stopped the game.");
		} else if (arg.equalsIgnoreCase("setcountdowntime")) {
			if (commandArgs.length() != 2) {
				commandArgs.getSender().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " setcountdowntime <value>");
				return;
			}
			if (game.hasStarted()) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has already started.");
				return;
			}
			if (!MathUtil.isInt(commandArgs.getArgs(1))) {
				commandArgs.getSender().sendMessage(ChatColor.RED + "Please enter a valid integer.");
				return;
			}
			game.setCountdownTime(Integer.valueOf(commandArgs.getArgs(1)));
			Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has set the countdown time to " + commandArgs.getArgs(1) + ".");
		}
	}

}
