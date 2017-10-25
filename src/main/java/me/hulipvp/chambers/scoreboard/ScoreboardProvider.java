package me.hulipvp.chambers.scoreboard;

import java.util.List;

import org.bukkit.entity.Player;

import me.hulipvp.chambers.Chambers;

public interface ScoreboardProvider {
	
	public Chambers plugin = Chambers.getInstance();

	String getTitle(Player player);

	List<String> getLines(Player player);
	
	public default String formatIntToTime(int i) {
		if (i < 3600) {
			int minutes = (i - (i % 60)) / 60;
			int seconds = i % 60;
			return ((minutes < 10) ? "0" + minutes : String.valueOf(minutes)) + ":"
					+ ((seconds < 10) ? "0" + seconds : String.valueOf(seconds));
		} else if (i > 3600 && i < 3600 * 24) {
			int minutes = (i / 60) % 60;
			int hours = (i / 60) / 60;
			int seconds = i % 60;
			return hours + ":" + ((minutes < 10) ? "0" + minutes : String.valueOf(minutes)) + ":"
					+ ((seconds < 10) ? "0" + seconds : String.valueOf(seconds));
		}
		return "00:00";
	}

}