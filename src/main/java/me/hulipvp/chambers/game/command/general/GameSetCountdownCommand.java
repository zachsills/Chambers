package me.hulipvp.chambers.game.command.general;

import me.hulipvp.chambers.game.command.GameCommand;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.util.MathUtil;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GameSetCountdownCommand extends GameCommand {

    @Command(name = "game.setcountdown", description = "Set the countdown time of the game", aliases = { "managegame.setcountdown" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Game game = plugin.getGameManager().getGame();
        if (commandArgs.length() != 1) {
            commandArgs.getSender().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <time>");
            return;
        }
        if (game.getStatus() != GameStatus.STARTING) {
            commandArgs.getPlayer().sendMessage(ChatColor.RED + "The countdown has already started.");
            return;
        }
        if (!MathUtil.isInt(commandArgs.getArgs(0))) {
            commandArgs.getSender().sendMessage(ChatColor.RED + "Please enter a valid integer.");
            return;
        }
        int time = Math.abs(Integer.valueOf(commandArgs.getArgs(1)));
        if (time == 0 || time > 120) {
            commandArgs.getSender().sendMessage(ChatColor.RED + "Please enter a time less than 120 and greater than 0.");
        }
        game.setCountdownTime(time);
        Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has set the countdown time to " + commandArgs.getArgs(1) + ".");
    }

}
