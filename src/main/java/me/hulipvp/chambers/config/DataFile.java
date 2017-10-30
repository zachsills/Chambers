package me.hulipvp.chambers.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * I'm too lazy to ask alex for permission to use this class, so I typed
 * everything line by line so basically this class wasn't written by him. But
 * I'll still give that kid some credit. So here it goes - this class was
 * created by bizarrealex<br>
 * (but I still typed it out line by line)
 *
 * @author bizarrealex
 */
public class DataFile {

	private File file;
	private YamlConfiguration configuration;

	public DataFile(JavaPlugin plugin, String name) {
		file = new File(plugin.getDataFolder(), name + ".yml");

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}

		plugin.saveResource(name + ".yml", false);

		configuration = YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Loads the file<br>
	 * You can also use this method to reload the file
	 */
	public void load() {
		configuration = YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * If you don't understand this, why are you even reading this
	 */
	public void save() {
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the configuration so you can access it
	 * 
	 * @return configuration - the YamlConfiguration object
	 */
	public YamlConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * Retrieve a double from the file<br>
	 * Will return as <tt>0.0</tt> if no double by the path name was found
	 * 
	 * @param path - the path of the double you're looking for
	 * @return double - the double which is found in the provided path
	 */
	public double getDouble(String path) {
		if (configuration.contains(path)) {
			return configuration.getDouble(path);
		}
		return 0.0;
	}

	/**
	 * Get an int from the file<br>
	 * Will returin as <tt>0</tt> is no int was found in the provided path
	 * 
	 * @param path - the path of the integer value that you're looking for
	 * @return int - the int which was found in the provided path
	 */
	public int getInt(String path) {
		if (configuration.contains(path)) {
			return configuration.getInt(path);
		}
		return 0;
	}

	/**
	 * Retrieve a boolean value from the file<br>
	 * Will return as a default value of <tt>false</tt> if there was nothing
	 * found in the provided path
	 * 
	 * @param path - the path of the boolean that you're looking for
	 * @return boolean - the boolean which was found in the provided path
	 */
	public boolean getBoolean(String path) {
		if (configuration.contains(path)) {
			return configuration.getBoolean(path);
		}
		return false;
	}

	/**
	 * Retrieve a String from the file<br>
	 * Will return an empty String if there was no String found in the provided
	 * path
	 * 
	 * @param path - the path of the String you're looking for
	 * @return String - the String which was found in the provided path
	 */
	public String getString(String path) {
		if (configuration.contains(path)) {
			return ChatColor.translateAlternateColorCodes('&', configuration.getString(path));
		}
		return "";
	}

	/**
	 * Retrieve a List of Strings from the file<br>
	 * Will return a list saying the path is invalid is no String List is found
	 * in the provided path
	 * 
	 * @param path - the path you wish to find a String list at
	 * @return strings - a List of Strings which was found in the provided path
	 */
	public List<String> getStringList(String path) {
		if (configuration.contains(path)) {
			List<String> strings = new ArrayList<>();
			configuration.getStringList(path).stream()
					.forEach(string -> strings.add(ChatColor.translateAlternateColorCodes('&', string)));
			return strings;
		}
		return Arrays.asList("Invalid path.");
	}

	/**
	 * Simply reverses a String list<br>
	 * I'm not really sure why this is needed, but okay
	 * 
	 * @param path - the path you wish to find a String list at
	 * @return list - a List of Strings that are in reversed order from which it
	 *         was found
	 */
	public List<String> getReversedStringList(String path) {
		List<String> list = getStringList(path);
		if (list != null) {
			Collections.reverse(list);
			return list;
		}
		return Arrays.asList("Invalid path.");
	}

}