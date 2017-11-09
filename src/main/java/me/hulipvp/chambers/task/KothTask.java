package me.hulipvp.chambers.task;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.koth.structure.Koth;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.util.MathUtil;

public class KothTask extends BukkitRunnable {
	
	private Map<Integer, Integer> times;
	
	public KothTask() {
		times.put(300, 300);
		times.put(1800, 240);
		times.put(2700, 180);
	}

	@Override
	public void run() {
		if (Chambers.getInstance().getKothManager().getKoth() != null) {
			Koth koth = Chambers.getInstance().getKothManager().getKoth();
			if (koth.getCapper() != null) {
				koth.setTime(koth.getTime() - 1);
			} else {
				koth.setTime(koth.getMaxCapTime());
			}
			if (koth.getCapper() == null && koth.getCapQueue().peek() != null) {
				Profile profile = koth.getCapQueue().poll();
				koth.setCapper(profile);
				profile.sendMessage(Chambers.getInstance().getKothManager().getKothFormat() + "You have started to control " + ChatColor.BLUE + koth.getName() + ChatColor.YELLOW + ".");
				profile.getTeam().sendMessage(Chambers.getInstance().getKothManager().getKothFormat() + "Your team has started to control " + ChatColor.BLUE + koth.getName() + ChatColor.YELLOW + ".");
			}
			adjustKothTime(koth);
		}
	}
	
	private void adjustKothTime(Koth koth) {
		int gameTime = Chambers.getInstance().getGameManager().getGame().getTotalTime();
		if (times.containsKey(gameTime)) {
			int capTime = times.get(gameTime);
			if (koth.getTime() > capTime) {
				koth.setTime(capTime);
			}
			koth.setMaxCapTime(capTime);
			Chambers.getInstance().getKothManager().broadcastMessage("The time has been reduced to " + ChatColor.BLUE + MathUtil.formatIntToTime(capTime));
		}
	}
	
}
