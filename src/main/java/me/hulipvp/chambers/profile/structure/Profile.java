package me.hulipvp.chambers.profile.structure;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.team.structure.Team;

@Getter
@Setter
public class Profile {

	private UUID id;
	private ProfileStatus profileStatus;
	private ChatStatus chatStatus;
	private Team team;
	private int balance, respawnTime;
	private boolean hidingScoreboard, bypassMode;

	public Profile(Player player, ProfileStatus profileStatus) {
		this.id = player.getUniqueId();
		this.profileStatus = profileStatus;
		this.chatStatus = ChatStatus.FACTION;
		this.team = null;
		this.balance = 500;
		this.respawnTime = 0;
		this.hidingScoreboard = false;
		this.bypassMode = false;
	}
	
	/**
	 * Get the Player object of the Profile
	 * 
	 * @return Player - the Player object of the Profile
	 */
	public Player getPlayer() {
		return Bukkit.getPlayer(this.getId());
	}

	/**
	 * Deposit money into the profile's balance
	 * 
	 * @param amount - the amount you wish to add to the profile
	 */
	public void deposit(int amount) {
		this.setBalance(this.getBalance() + amount);
	}

	/**
	 * Withdraw money from the profile's balance
	 * 
	 * @param amount - the amount you wish to take away from the profile
	 */
	public void withdraw(int amount) {
		this.setBalance(this.getBalance() - amount);
	}

	/**
	 * Send a player a message
	 * 
	 * @param message - the message you wish to send the player
	 */
	public void sendMessage(String message) {
		this.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

}