package me.yagi.game.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class NPCManager {
	
	private static List<String> npcids = new ArrayList<>();

	public static void createNPCFile() {
		 File npcfile = new File("plugins/Experimental/Others/NPC/", "texts.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(npcfile);
		 
		 npcids.add("Hey, wie gehts dir so?");
		 config.set("0.Text", npcids);
		 config.set("0.Name Prefix", "§6");
		 config.set("0.Message Prefix", "§e");	
		 
		 try {
			 config.save(npcfile);
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static void setHorse(Player p, int id, boolean spawned) {
		File npcfile = new File("plugins/Experimental/Others/NPC/PlayerHorses/", p.getUniqueId().toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(npcfile);
		
		config.set(p.getUniqueId().toString()+".Player Name", p.getName());
		config.set(p.getUniqueId().toString()+".Horse Spawned", spawned);
		config.set(p.getUniqueId().toString()+".Horse ID", id);
		
		try {
			config.save(npcfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void unmountPlayerFromHorse(Player p, int id) {
		File npcfile = new File("plugins/Experimental/Others/NPC/PlayerHorses/", p.getUniqueId().toString()+".yml");
		
		if(npcfile.exists()) {
			npcfile.delete();
		} else {
			System.out.println("Error: This file does not exist!");
		}
	}
	
	public static int getHorseIdByPlayer (Player p) {
		File npcfile = new File("plugins/Experimental/Others/NPC/PlayerHorses/", p.getUniqueId().toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(npcfile);
		
		return config.getInt(p.getUniqueId().toString()+".Horse ID");
		
	}
	
	public static boolean checkNPCFile() {
		File npcfile = new File("plugins/Experimental/Others/NPC/", "texts.yml");
		if(npcfile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<String> getNPCText(int id) {
		 File npcfile = new File("plugins/Experimental/Others/NPC/", "texts.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(npcfile);
		
		 return config.getStringList(String.valueOf(id)+".Text");
	}
	
	public static String getMessagePrefix(int id) {
		 File npcfile = new File("plugins/Experimental/Others/NPC/", "texts.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(npcfile);
		
		 return config.getString(String.valueOf(id)+".Message Prefix");
	}
	
	public static String getNamePrefix(int id) {
		 File npcfile = new File("plugins/Experimental/Others/NPC/", "texts.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(npcfile);
		
		 return config.getString(String.valueOf(id)+".Name Prefix");
	}
	
	public static boolean isTextAvailable(int id) {
		if(NPCManager.getNPCText(id) == null || NPCManager.getNPCText(id).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String removeColorCodes(String name) {
		String returnname = name.replaceAll("§0", "");
		returnname = returnname.replaceAll("§1", "");
		returnname = returnname.replaceAll("§2", "");
		returnname = returnname.replaceAll("§3", "");
		returnname = returnname.replaceAll("§4", "");
		returnname = returnname.replaceAll("§5", "");
		returnname = returnname.replaceAll("§6", "");
		returnname = returnname.replaceAll("§7", "");
		returnname = returnname.replaceAll("§8", "");
		returnname = returnname.replaceAll("§9", "");
		returnname = returnname.replaceAll("§a", "");
		returnname = returnname.replaceAll("§b", "");
		returnname = returnname.replaceAll("§c", "");
		returnname = returnname.replaceAll("§d", "");
		returnname = returnname.replaceAll("§e", "");
		returnname = returnname.replaceAll("§f", "");		
		return returnname;
	}
	
}
