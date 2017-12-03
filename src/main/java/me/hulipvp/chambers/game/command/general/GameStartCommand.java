package me.hulipvp.chambers.game.command.general;

import me.hulipvp.chambers.game.command.GameCommand;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GameStartCommand extends GameCommand {

    @Command(name = "game.start", description = "Start the game", aliases = { "managegame.start" }, usage = "/<command>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Game game = plugin.getGameManager().getGame();
        if (game.hasStarted()) {
            commandArgs.getPlayer().sendMessage(ChatColor.RED + "The game has already started.");
            return;
        }
        plugin.getGameManager().start();
        Bukkit.broadcast("chambers.staff", ChatColor.GREEN + commandArgs.getSender().getName() + ChatColor.YELLOW + " has forcefully started the countdown.");
    }

}
