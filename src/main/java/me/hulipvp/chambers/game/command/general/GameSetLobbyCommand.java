package me.hulipvp.chambers.game.command.general;

import me.hulipvp.chambers.game.command.GameCommand;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.LocationUtil;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.ChatColor;

public class GameSetLobbyCommand extends GameCommand {

    @Command(name = "game.setlobbyspawn", description = "Set the lobby spawn of the game", aliases = { "managegame.setlobbyspawn" }, usage = "/<command>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Game game = plugin.getGameManager().getGame();
        game.setSpawnLocation(commandArgs.getPlayer().getLocation());
        plugin.getDataFile().getConfiguration().set("SPAWN_LOCATION", LocationUtil.serializeLocation(commandArgs.getPlayer().getLocation()));
        saveDataFile();
        commandArgs.getSender().sendMessage(ChatColor.YELLOW + "You have set the Lobby spawn to: " + ChatColor.GREEN + LocationUtil.serializeLocation(commandArgs.getPlayer().getLocation()));
    }

}
