package me.hulipvp.chambers.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.util.LocationUtil;
import me.hulipvp.chambers.util.MathUtil;
import me.hulipvp.chambers.command.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class GameCommand extends ChambersCommand {

	@Command(name = "game", description = "The main command to manage the Game", aliases = { "managegame" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		Game game = plugin.getGameManager().getGame();
		if (game == null) {
			commandArgs.getSender().sendMessage(ChatColor.RED+ "There is no game to manage. Please restart this server as an error had occured while enabling the plugin.");
			return;
		}
		if (commandArgs.length() < 1) {
			commandArgs.getSender().sendMessage(ChatColor.RED + "/game <start|stop|setcountdowntime|setlobbyspawn>");
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
			if (game.getStatus() != GameStatus.STARTING) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "The countdown has already started.");
				return;
			}
			if (!MathUtil.isInt(commandArgs.getArgs(1))) {
				commandArgs.getSender().sendMessage(ChatColor.RED + "Please enter a valid integer.");
				return;
			}
			int time = Math.abs(Integer.valueOf(commandArgs.getArgs(1)));
			if (time == 0 || time > 120) {
				commandArgs.getSender().sendMessage(ChatColor.RED + "Please enter a time less than 120 and greater than 0.");
			}
			game.setCountdownTime(time);
			Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has set the countdown time to " + commandArgs.getArgs(1) + ".");
		} else if (arg.equalsIgnoreCase("setmapname")) {
			if (commandArgs.length() != 2) {
				commandArgs.getSender().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " setmapname <name>");
				return;
			}
			String mapName = commandArgs.getArgs(1);
			game.setMapName(mapName);
			plugin.getDataFile().getConfiguration().set("MAP_NAME", mapName);
			plugin.getDataFile().save();
			plugin.getDataFile().load();
			commandArgs.getSender().sendMessage(ChatColor.YELLOW + "The map name has been set to " + ChatColor.GREEN + mapName + ChatColor.YELLOW + ".");
		} else if (arg.equalsIgnoreCase("setkothname")) {
			if (commandArgs.length() != 2) {
				commandArgs.getSender().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " setkothname <name>");
				return;
			}
			String kothName = commandArgs.getArgs(1);
			game.setKothName(kothName);
			plugin.getDataFile().getConfiguration().set("KOTH_NAME", kothName);
			plugin.getDataFile().save();
			plugin.getDataFile().load();
			commandArgs.getSender().sendMessage(ChatColor.YELLOW + "The Koth name has been set to " + ChatColor.GREEN + kothName + ChatColor.YELLOW + ".");
		} else if (arg.equalsIgnoreCase("setlobbyspawn")) {
			game.setSpawnLocation(commandArgs.getPlayer().getLocation());
			plugin.getDataFile().getConfiguration().set("SPAWN_LOCATION", LocationUtil.serializeLocation(commandArgs.getPlayer().getLocation()));
			plugin.getDataFile().save();
			plugin.getDataFile().load();
			commandArgs.getSender().sendMessage(ChatColor.YELLOW + "You have set the Lobby spawn to: " + ChatColor.GREEN + LocationUtil.serializeLocation(commandArgs.getPlayer().getLocation()));
		}
	}
	
}
