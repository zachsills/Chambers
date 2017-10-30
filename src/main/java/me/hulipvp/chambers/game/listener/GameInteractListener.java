package me.hulipvp.chambers.game.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.Team;
import net.md_5.bungee.api.ChatColor;

public class GameInteractListener implements Listener {
	
	private Chambers plugin;
	private Map<Material, Team> teamMap;
	
	public GameInteractListener() {
		
		plugin = Chambers.getInstance();
		teamMap = new HashMap<>();
		
		teamMap.put(Material.REDSTONE, plugin.getTeamManager().getTeamByName("Red"));
		teamMap.put(Material.DIAMOND, plugin.getTeamManager().getTeamByName("Blue"));
		teamMap.put(Material.EMERALD, plugin.getTeamManager().getTeamByName("Green"));
		teamMap.put(Material.GOLD_INGOT, plugin.getTeamManager().getTeamByName("Yellow"));
		teamMap.put(Material.NETHER_STAR, null);
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (!event.getAction().name().contains("RIGHT")) {
			return;
		}
		Game game = plugin.getGameManager().getGame();
		if (!game.hasStarted()) {
			event.setCancelled(true);
			return;
		}
		Player player = event.getPlayer();
		Profile profile = plugin.getProfileManager().getProfileByUuid(player.getUniqueId());
		ItemStack item = player.getItemInHand();
		if (item == null || item.getType() == Material.AIR) {
			event.setCancelled(true);
			return;
		}
		Material material = item.getType();
		if (!teamMap.containsKey(material)) {
			event.setCancelled(true);
			return;
		}
		Team team = teamMap.get(material);
		if (team == null) {
			plugin.getTeamManager().getRandomTeam().addMember(profile);
		} else {
			if (team.isFull()) {
				player.sendMessage(ChatColor.RED + "That team is currently full. Please join another team.");
				return;
			}
			team.addMember(profile);
		}
		if (profile.getTeam() != null) {
			if (profile.getTeam() == team) {
				return;
			}
			profile.sendMessage(ChatColor.GRAY + "You have joined team " + profile.getTeam().getFormattedName() + ChatColor.GRAY + "." + ChatColor.RED + " (" + profile.getTeam().getSize() + "/5)");
		}
	}

}
