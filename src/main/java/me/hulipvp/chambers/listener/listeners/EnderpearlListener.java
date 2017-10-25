package me.hulipvp.chambers.listener.listeners;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.util.Color;
import net.minecraft.util.com.google.common.collect.Sets;

public class EnderpearlListener implements Listener {
	private int enderpearlCooldownTime;
	private Set<Material> blockedPearlTypes;
	private static Map<Player, Long> enderpearlCooldownMap;

	public EnderpearlListener() {
		enderpearlCooldownMap = new HashMap<>();
		enderpearlCooldownTime = 16;
		blockedPearlTypes = Sets.immutableEnumSet(Material.THIN_GLASS,
				new Material[] { Material.IRON_FENCE, Material.FENCE, Material.NETHER_FENCE, Material.FENCE_GATE,
						Material.ACACIA_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.BRICK_STAIRS,
						Material.COBBLESTONE_STAIRS, Material.DARK_OAK_STAIRS, Material.JUNGLE_WOOD_STAIRS,
						Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS, Material.SANDSTONE_STAIRS,
						Material.SMOOTH_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.WOOD_STAIRS });
	}

	public void putCooldown(Player player, long time) {
		long value = System.currentTimeMillis() + time * 1000L;
		enderpearlCooldownMap.put(player, value);
	}

	public void removeCooldown(Player player) {
		enderpearlCooldownMap.remove(player);
		player.setLevel(0);
		player.setExp(0.0F);
	}

	public static boolean hasCooldown(Player player) {
		if (!enderpearlCooldownMap.containsKey(player)) {
			return false;
		}
		long value = enderpearlCooldownMap.get(player);
		return value > System.currentTimeMillis();
	}

	public static long getMillisecondsLeft(Player player) {
		if (!hasCooldown(player)) {
			return -1L;
		}
		return enderpearlCooldownMap.get(player) - System.currentTimeMillis();
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		removeCooldown(player);
	}

	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		Player player = event.getPlayer();
		removeCooldown(player);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		removeCooldown(player);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			Material material = event.getMaterial();
			Player player = event.getPlayer();
			if (material == Material.ENDER_PEARL && player.getGameMode() != GameMode.CREATIVE) {
				if (event.getClickedBlock() != null) {
					Block block = event.getClickedBlock();
					if ((block.getType().isSolid()) && (!(block.getState() instanceof InventoryHolder))) {
						event.setCancelled(true);
						player.setItemInHand(event.getItem());
						return;

					}
				}
				if (hasCooldown(player)) {
					event.setUseItemInHand(Event.Result.DENY);
					player.sendMessage(ChatColor.GOLD + "You must wait " + ChatColor.RESET + String.valueOf(new DecimalFormat("0.0").format(getMillisecondsLeft(player) / 1000.0)) + ChatColor.GOLD + " seconds.");
				} else {
					putCooldown(player, enderpearlCooldownTime);
					new BukkitRunnable() {
						public void run() {
							ItemStack itemStack = player.getItemInHand();
							if (itemStack != null && itemStack.getType() == Material.ENDER_PEARL
									&& player.getGameMode() != GameMode.CREATIVE) {
								if (hasCooldown(player)) {
									ItemMeta itemMeta = itemStack.getItemMeta();
									itemMeta.setDisplayName(ChatColor.RED + String.valueOf(new DecimalFormat("0.0").format(getMillisecondsLeft(player) / 1000.0)) + "s");
									itemStack.setItemMeta(itemMeta);
								} else if (itemStack.getItemMeta().hasDisplayName()) {
									ItemMeta itemMeta = itemStack.getItemMeta();
									itemMeta.setDisplayName(null);
									itemStack.setItemMeta(itemMeta);
									player.sendMessage(Color.color("&aYou can now throw a pearl again."));
									player.setLevel(0);
									player.setExp(0.0F);
									this.cancel();
								}
							}
							if (hasCooldown(player)) {
								int enderPearlCooldownTimeRemaining = (int) (getMillisecondsLeft(player) / 1000.0);
								if (player.getLevel() != enderPearlCooldownTimeRemaining) {
									player.setLevel(enderPearlCooldownTimeRemaining);
								}

								// player.setExp(player.getExp() -
								// 0.99F / ((float) (16 * 20F) / 2F));
							}
						}
					}.runTaskTimer(Chambers.getInstance(), 1L, 2L);
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		ItemStack itemStack = event.getCurrentItem();
		if (itemStack != null && itemStack.getType() == Material.ENDER_PEARL) {
			ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
			itemMeta.setDisplayName(null);
			event.getCurrentItem().setItemMeta(itemMeta);
		}
	}

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
		ItemStack itemStack = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
		if (itemStack != null && itemStack.getType() == Material.ENDER_PEARL && itemStack.getItemMeta() != null) {
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(null);
			itemStack.setItemMeta(itemMeta);
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPearlClip(PlayerTeleportEvent event) {
		if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
			Location to = event.getTo();
			if (blockedPearlTypes.contains(to.getBlock().getType())) {
				Player player = event.getPlayer();
				player.sendMessage(ChatColor.RED + "You cannot pearl there.");
				removeCooldown(player);
				event.setCancelled(true);

				return;
			}
			to.setX(to.getBlockX() + 0.5D);
			to.setZ(to.getBlockZ() + 0.5D);
			event.setTo(to);

		}
	}
}
