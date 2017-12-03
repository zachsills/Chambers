package me.hulipvp.chambers.game.command;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.util.Color;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameCommand {

    public Chambers plugin = Chambers.getInstance();

    public GameCommand() {
        plugin.getCommandFramework().registerCommands(this);
        plugin.getCommandFramework().registerHelp();
    }

    @Command(name = "game", description = "The main command to manage the Game", aliases = { "managegame" }, usage = "/<command>", requiresTeam = false, adminsOnly = true)
    public void onCommand(CommandArgs commandArgs) {
        Player player = (Player) commandArgs.getSender();
        Game game = plugin.getGameManager().getGame();
        if (game == null) {
            commandArgs.getSender().sendMessage(ChatColor.RED+ "There is no game to manage. Please restart this server as an error had occured while enabling the plugin.");
            return;
        }
        showHelp(player);
    }

    public void showHelp(Player player) {
        player.sendMessage(Color.color("&6&m-------------------------------------------------------------"));
        player.sendMessage(Color.color("&d&lGame Help&7 - &fInformation on how to use faction command"));
        player.sendMessage(Color.color("&6&m-------------------------------------------------------------"));
        player.sendMessage(Color.color("&9General Commands:"));
        player.sendMessage(Color.color("&e/game start &7- Start the game"));
        player.sendMessage(Color.color("&e/game stop [Optional: <team>] &7- Stop the game"));
        player.sendMessage(Color.color("&e/game setcountdown <time> &7- Set the countdown time"));
        player.sendMessage(Color.color("&e/game setmapname <name> &7- Set the Map name"));
        player.sendMessage(Color.color("&e/game setkothname <name> &7- Set the Koth's name"));
        player.sendMessage(Color.color("&e/game setlobbyspawn &7- Set the spawn of the lobby"));
        player.sendMessage(Color.color("&6&m--------------------------------------------------------------"));
    }

    public void saveDataFile() {
        plugin.getDataFile().save();
        plugin.getDataFile().load();
    }

}
