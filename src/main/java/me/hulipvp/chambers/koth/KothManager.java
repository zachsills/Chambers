package me.hulipvp.chambers.koth;

import org.bukkit.Bukkit;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.koth.structure.Koth;
import net.md_5.bungee.api.ChatColor;

@Getter
@Setter
public class KothManager {
	
	private Koth koth;
	
	public KothManager() {
		
		koth = null;
		
	}
	
	/**
	 * Broadcast a message in the KOTH message format
	 * 
	 * @param message - the message you wish to send in KOTH format
	 */
	public void broadcastMessage(String message) {
		Bukkit.broadcastMessage(ChatColor.GOLD + "[King of the Hill] " + ChatColor.YELLOW + message);
	}

}
