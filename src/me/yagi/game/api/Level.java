package me.yagi.game.api;

import java.awt.Color;
import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.inventivegames.particle.ParticleEffect;

public class Level {

	public static void createLevelFile() {
		File file = new File("plugins/Experimental/Others/", "levels.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		int i = 2;
		
		config.set("Level 1", 0);
		config.set("Level 2", 100);
		while(i != 60) {
			i++;
			config.set("Level "+i, (config.getInt("Level "+(i-1))*1.25));
		}
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean checkLevelFile() {
		File file = new File("plugins/Experimental/Others/", "levels.yml");
		if(file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getLevelEXP(int level) {
		File file = new File("plugins/Experimental/Others/", "levels.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getInt("Level "+level);
	}
	
	public static boolean checkForLevelUp(Player p) {
		int level = PlayerFile.getLevel(p);
		int rawexp = PlayerFile.getRawEXP(p);
		if(rawexp >= Level.getLevelEXP(level+1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean reachedLevelCap(Player p) {
		int level = PlayerFile.getLevel(p);
		if(level == 60) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void levelUpMessage(Player p) {
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("§8§m-----------------------");
		p.sendMessage("   §6§lCONGRATULATIONS");
		p.sendMessage("§6");
		p.sendMessage("§7You have leveled up!");
		p.sendMessage("§7You are now §6Level "+PlayerFile.getLevel(p)+"§7!");
		p.sendMessage("§7Don't forget to try out all the\nnew items for your level!");
		p.sendMessage("§8§m-----------------------");
		p.sendMessage("");
		
		try {
			ParticleEffect.HEART.sendToPlayers(Bukkit.getOnlinePlayers(), p.getLocation(), (float)0, (float) 1, (float)0, (float)1, (int)1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Extras.playSound(p, Sound.ENTITY_FIREWORK_LARGE_BLAST);
		Extras.playSound(p, Sound.ENTITY_FIREWORK_TWINKLE);
	}
	
}
