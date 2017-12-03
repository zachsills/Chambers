package me.hulipvp.chambers.game.command.general;

import me.hulipvp.chambers.game.command.GameCommand;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.ChatColor;

public class GameSetKothNameCommand extends GameCommand {

    @Command(name = "game.setkothname", description = "Set the name of the koth", aliases = { "managegame.setkothname" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Game game = plugin.getGameManager().getGame();
        if (commandArgs.length() != 1) {
            commandArgs.getSender().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <name>");
            return;
        }
        String kothName = commandArgs.getArgs(1);
        game.setKothName(kothName);
        plugin.getDataFile().getConfiguration().set("KOTH_NAME", kothName);
        saveDataFile();
        commandArgs.getSender().sendMessage(ChatColor.YELLOW + "The Koth name has been set to " + ChatColor.GREEN + kothName + ChatColor.YELLOW + ".");
    }

}
