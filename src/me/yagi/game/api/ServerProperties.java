package me.yagi.game.api;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ServerProperties {

	private static List<String> motd = new ArrayList<>();
	private static List<String> players_uuid = new ArrayList<>();
	private static List<String> blacklist_uuid = new ArrayList<>();
	private static List<String> items_list = new ArrayList<>();
	private static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private static Date dateobj = new Date();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");  
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("[HH:mm:ss] -");  
	
	public static void createServerFiles() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		motd.add("§7Currently under maintenance!");
		motd.add("§7System has been coded by Yagi");
		
		players_uuid.add(Bukkit.getOfflinePlayer("Firelove").getUniqueId().toString());
		blacklist_uuid.add(Bukkit.getOfflinePlayer("GommeHD").getUniqueId().toString());
		items_list.add("Ethereal");
		
		config.set("General.Slots", 1923);
		config.set("General.Motd_line1", "§cDevelopment Test Server");
		config.set("General.Motd_line2", motd);
		config.set("Whitelist.Status", false);
		config.set("Whitelist.Players", players_uuid);
		config.set("Blacklist.Status", false);
		config.set("Blacklist.Players", blacklist_uuid);
		config.set("Items.List", items_list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createLog() {
		Date date = new Date(); 
		File file = new File("plugins/Experimental/Others/Logs/", "server_log_"+formatter.format(date)+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set("Start of the Log: ", df.format(dateobj));
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean logExists() {
		Date date = new Date(); 
		File file = new File("plugins/Experimental/Others/Logs/", "server_log_"+formatter.format(date)+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if(file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void setNewMessage(Player p, String message) {
		Date date = new Date(); 
		File file = new File("plugins/Experimental/Others/Logs/", "server_log_"+formatter.format(date)+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set(formatter2.format(date), p.getName()+": "+message);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setNewCommand(Player p, String command) {
		Date date = new Date(); 
		File file = new File("plugins/Experimental/Others/Logs/", "server_log_"+formatter.format(date)+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set(formatter2.format(date), p.getName()+" executed the /"+command+" command!");
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean checkServerFiles() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		if(file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getSlots() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getInt("General.Slots");
	}
	
	public static List<String> getMotdLine2() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getStringList("General.Motd_line2");
	}
	
	public static String getMotdLine1() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getString("General.Motd_line1");
	}
	
	public static boolean getWhitelistStatus() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("Whitelist.Status");
	}
	
	public static void setWhitelistStatus(boolean status) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set("Whitelist.Status", status);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getBlacklistStatus() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("Blacklist.Status");
	}
	
	public static List<String> getWhitelistedPlayers() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getStringList("Whitelist.Players");
	}
	
	public static void addWhitelistedPlayer(String uuid) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		List<String> list = getWhitelistedPlayers();
		list.add(uuid);
		
		config.set("Whitelist.Players", list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void removeWhitelistedPlayers(String uuid) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		List<String> list = getWhitelistedPlayers();
		list.remove(uuid);
		
		config.set("Whitelist.Players", list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addBlacklistedPlayer(String uuid) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		List<String> list = getBlacklistedPlayers();
		list.add(uuid);
		
		config.set("Blacklist.Players", list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void removeBlacklistedPlayer(String uuid) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		List<String> list = getBlacklistedPlayers();
		list.remove(uuid);
		
		config.set("Blacklist.Players", list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getBlacklistedPlayers() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getStringList("Blacklist.Players");
	}

	public static void setSlots(int amount) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set("General.Slots", amount);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getItemList() {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getStringList("Items.List");
	}
	
	public static void setItemList(List<String> items_list) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set("Items.List", items_list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addItemToList(String itemname) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		items_list = config.getStringList("Items.List");
		items_list.add(itemname);
		config.set("Items.List", items_list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void removeItemFromList(String itemname) {
		File file = new File("plugins/Experimental/Others/", "server_properties.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		items_list = config.getStringList("Items.List");
		try {
			items_list.remove(itemname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		config.set("Items.List", items_list);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
