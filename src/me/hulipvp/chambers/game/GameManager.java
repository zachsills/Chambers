package me.hulipvp.chambers.game;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.task.CountdownTask;
import me.hulipvp.chambers.task.GameTask;
import me.hulipvp.chambers.team.structure.Team;

@Getter
public class GameManager {

	private Game game;

	public GameManager() {

		game = new Game(GameStatus.LOBBY);

	}

	/**
	 * Starts the Game
	 */
	public void start() {
		game.setStatus(GameStatus.STARTING);
		Arrays.stream(Bukkit.getOnlinePlayers()).forEach(player -> player.getInventory().clear());
		new CountdownTask().runTaskTimerAsynchronously(Chambers.getInstance(), 0L, 20L);
		Bukkit.broadcastMessage(ChatColor.YELLOW + "The countdown is now starting");
	}

	/**
	 * Stops the Game
	 */
	public void stop(Team winner) {
		game.setStatus(GameStatus.OVER);
		Arrays.stream(Bukkit.getOnlinePlayers()).forEach(player -> {
			Arrays.stream(Bukkit.getOnlinePlayers()).forEach(other -> {
				player.showPlayer(other);
				other.showPlayer(player);
			});
			player.setGameMode(GameMode.CREATIVE);
			player.getInventory().clear();
			player.updateInventory();
			player.getInventory().setArmorContents(null);
			player.setHealth(20.0);
			player.setFoodLevel(20);
			player.setFireTicks(0);
			player.setGameMode(GameMode.SURVIVAL);
			player.setExp(0.0F);
			player.setAllowFlight(true);
			player.setFlying(true);
			player.getActivePotionEffects().stream().forEach(effect -> player.removePotionEffect(effect.getType()));
		});
		game.setTotalTime(0);
		game.setWinner(winner);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Chambers.getInstance(), () -> {
			int time = 15;
			if (time == -2) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
			} else if (time == 0) {
				Arrays.stream(Bukkit.getOnlinePlayers()).forEach(player -> player.kickPlayer("Thanks for playing Chambers beta, join back soon!"));
			} else if (time % 2 == 0) {
				Bukkit.broadcastMessage(ChatColor.YELLOW + (winner == null ? "The game was forcefully stopped." : "The team " + winner.getFormattedName() + ChatColor.YELLOW + "has won the game!"));
			}
			time--;
		}, 0, 20L);
	}

	/**
	 * Attempts to start the Game
	 */
	public void tryStart() {
		if (Bukkit.getOnlinePlayers().length == 20) {
			start();
		}
	}

	/**
	 * Finally starts the game after the countdown
	 * 
	 * You have to load the chunk because the player could probably spawn in the
	 * ground if the chunk is not loaded when the player is teleported
	 */
	public void finallyStart() {
		game.setStatus(GameStatus.INGAME);
		Chambers.getInstance().getTeamManager().getAllPlayerTeams().stream().forEach(team -> {
			if (!team.getHome().getChunk().isLoaded()) {
				team.getHome().getChunk().load();
			}
			team.getOnlinePlayers().stream().forEach(player -> {
				giveStartingItems(player);
				player.setGameMode(GameMode.SURVIVAL);
				player.setAllowFlight(false);
				player.setFlying(false);
				player.teleport(team.getHome());
			});
		});
		new GameTask().runTaskTimerAsynchronously(Chambers.getInstance(), 0L, 20L);
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage(ChatColor.YELLOW + "The game has now started! Good luck!");
		Bukkit.broadcastMessage(" ");
	}

	/**
	 * Give a player their starting items
	 * 
	 * @param player - the Player you wish to give the items to
	 */
	public static void giveStartingItems(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
		player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
		player.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
	}

}
