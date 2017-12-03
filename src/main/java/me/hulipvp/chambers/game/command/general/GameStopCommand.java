package me.hulipvp.chambers.game.command.general;

import me.hulipvp.chambers.game.command.GameCommand;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GameStopCommand extends GameCommand {

    @Command(name = "game.stop", description = "Stop the game", aliases = { "managegame.stop" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Game game = plugin.getGameManager().getGame();
        if (!game.hasStarted()) {
            commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has not started yet.");
            return;
        }
        Team team = null;
        if (commandArgs.length() == 1) {
            team = plugin.getTeamManager().getTeamByName(commandArgs.getArgs(0));
        }
        plugin.getGameManager().stop(team);
        Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has forcefully stopped the game.");
    }

}
