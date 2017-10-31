package me.hulipvp.chambers.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

/**
 * This is a chainable builder for {@link ItemStack}s in {@link Bukkit} <br>
 * Example Usage:<br>
 * {@code ItemStack is = new ItemUtil(Material.LEATHER_HELMET).amount(2).data(4).durability(4).enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.LUCK, 2).name(ChatColor.RED + "the name").lore(ChatColor.GREEN + "line 1").lore(ChatColor.BLUE + "line 2").color(Color.MAROON).build();
 *
 * @author MiniDigger, edited by Alexandeh
 * @version 1.3
 */
public class ItemUtil {

	private ItemStack is;

	/**
	 * Inits the builder with the given {@link Material}
	 *
	 * @param mat
	 *            the {@link Material} to start the builder from
	 * @since 1.0
	 */
	public ItemUtil(Material mat) {
		is = new ItemStack(mat);
	}

	/**
	 * Inits the builder with the given {@link ItemStack}
	 *
	 * @param is
	 *            the {@link ItemStack} to start the builder from
	 * @since 1.0
	 */
	public ItemUtil(ItemStack is) {
		this.is = is;
	}

	/**
	 * Changes the amount of the {@link ItemStack}
	 *
	 * @param amount
	 *            the new amount to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil amount(int amount) {
		is.setAmount(amount);
		return this;
	}

	/**
	 * Changes the display name of the {@link ItemStack}
	 *
	 * @param name
	 *            the new display name to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil name(String name) {
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(name);
		is.setItemMeta(meta);
		return this;
	}

	/**
	 * Adds a new line to the lore of the {@link ItemStack}
	 *
	 * @param name
	 *            the new line to add
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil lore(String name) {
		ItemMeta meta = is.getItemMeta();
		List<String> lore = meta.getLore();
		if (lore == null) {
			lore = new ArrayList<String>();
		}
		lore.add(name);
		meta.setLore(lore);
		is.setItemMeta(meta);
		return this;
	}

	/**
	 * sets the lore of the {@link ItemStack}
	 *
	 * @param lore
	 *            the loret o set
	 * @return this builder for chaining
	 * @since 1.2
	 */
	public ItemUtil lore(List<String> lore) {
		ItemMeta meta = is.getItemMeta();
		meta.setLore(lore);
		is.setItemMeta(meta);
		return this;
	}

	/**
	 * Changes the durability of the {@link ItemStack}
	 *
	 * @param durability
	 *            the new durability to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil durability(int durability) {
		is.setDurability((short) durability);
		return this;
	}

	/**
	 * Changes the data of the {@link ItemStack}
	 *
	 * @param data
	 *            the new data to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	@SuppressWarnings("deprecation")
	public ItemUtil data(int data) {
		is.setData(new MaterialData(is.getType(), (byte) data));
		return this;
	}

	/**
	 * Adds an {@link Enchantment} with the given level to the {@link ItemStack}
	 *
	 * @param enchantment
	 *            the enchantment to add
	 * @param level
	 *            the level of the enchantment
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil enchantment(Enchantment enchantment, int level) {
		is.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	/**
	 * Adds an {@link Enchantment} with the level 1 to the {@link ItemStack}
	 *
	 * @param enchantment
	 *            the enchantment to add
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil enchantment(Enchantment enchantment) {
		is.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	/**
	 * Changes the {@link Material} of the {@link ItemStack}
	 *
	 * @param material
	 *            the new material to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil type(Material material) {
		is.setType(material);
		return this;
	}

	/**
	 * Clears the lore of the {@link ItemStack}
	 *
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil clearLore() {
		ItemMeta meta = is.getItemMeta();
		meta.setLore(new ArrayList<String>());
		is.setItemMeta(meta);
		return this;
	}

	/**
	 * Clears the list of {@link Enchantment}s of the {@link ItemStack}
	 *
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public ItemUtil clearEnchantments() {
		for (Enchantment e : is.getEnchantments().keySet()) {
			is.removeEnchantment(e);
		}
		return this;
	}

	/**
	 * Sets the {@link Color} of a part of leather armor
	 *
	 * @param color
	 *            the {@link Color} to use
	 * @return this builder for chaining
	 * @since 1.1
	 */
	public ItemUtil color(Color color) {
		if (is.getType() == Material.LEATHER_BOOTS || is.getType() == Material.LEATHER_CHESTPLATE || is.getType() == Material.LEATHER_HELMET || is.getType() == Material.LEATHER_LEGGINGS) {
			LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
			return this;
		} else {
			throw new IllegalArgumentException("color() only applicable for leather armor!");
		}
	}

	/**
	 * Builds the {@link ItemStack}
	 *
	 * @return the created {@link ItemStack}
	 * @since 1.0
	 */
	public ItemStack build() {
		return is;
	}

}