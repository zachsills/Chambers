package me.hulipvp.chambers.util.commandapi;

import me.hulipvp.chambers.Chambers;

public class ChambersCommand {

	public Chambers plugin = Chambers.getInstance();

	public ChambersCommand() {
		plugin.getCommandFramework().registerCommands(this);
		plugin.getCommandFramework().registerHelp();
	}

}
