package me.hulipvp.chambers.game.command.general;

import me.hulipvp.chambers.game.command.GameCommand;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.ChatColor;

public class GameSetMapNameCommand extends GameCommand {

    @Command(name = "game.setmapname", description = "Set the map name of the game", aliases = { "managegame.setmapname" }, usage = "/<command> <args>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Game game = plugin.getGameManager().getGame();
        if (commandArgs.length() != 1) {
            commandArgs.getSender().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <name>");
            return;
        }
        String mapName = commandArgs.getArgs(1);
        game.setMapName(mapName);
        plugin.getDataFile().getConfiguration().set("MAP_NAME", mapName);
        saveDataFile();
        commandArgs.getSender().sendMessage(ChatColor.YELLOW + "The map name has been set to " + ChatColor.GREEN + mapName + ChatColor.YELLOW + ".");
    }

}
