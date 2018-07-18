package me.yagi.game.api;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerFile {
	
	private static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private static Date dateobj = new Date();

	public static boolean exists(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		
		if(playerfile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getTitle(Player p) {
		if(PlayerFile.getGMStatus(p)) {
			return "§3❆ §3GM";
		} else if(PlayerFile.getBuilderStatus(p)) {
			return "§3❆ §3Build";
		} else {
			if(PlayerFile.getLevel(p) <= 4) {
				return "§f❆ §fTrainee";
			} else if(PlayerFile.getLevel(p) <= 14) {
				return "§7❆ §7Squire";
			} else if(PlayerFile.getLevel(p) <= 29) {
				return "§2❆ §2Knight";
			} else if(PlayerFile.getLevel(p) <= 39) {
				return "§c❆ §cBaron";
			} else if(PlayerFile.getLevel(p) <= 54) {
				return "§c❆ §cLord";
			} else if(PlayerFile.getLevel(p) <= 60) {
				return "§6❆ §6Duke";
			} else {
				return "§6❆ §6King";
			}
		}
	}

	public static void create(Player p) {

		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("PLAYER.CURRENT NAME", p.getName());
		config.set("PLAYER.RANK", "NORMAL");
		config.set("PLAYER.INVENTORY", p.getInventory().getStorageContents());
		config.set("PLAYER.ONLINE", true);
		config.set("PLAYER.FIRST JOIN", df.format(dateobj));
		config.set("PLAYER.LAST JOIN", df.format(dateobj));
		config.set("HEALTH.CURRENT", 10);
		config.set("HEALTH.MAX", 10);
		config.set("LEVEL.LEVEL", 1);
		config.set("LEVEL.EXP", 0.0);
		config.set("LEVEL.RAW_EXP", 0);
		config.set("LOCATION.X", p.getLocation().getX());
		config.set("LOCATION.Y", p.getLocation().getY());
		config.set("LOCATION.Z", p.getLocation().getZ());
		config.set("LOCATION.PITCH", p.getLocation().getPitch());
		config.set("LOCATION.YAW", p.getLocation().getYaw());
		config.set("MISC.FRIEND", false);
		config.set("CONTENT.GAMEMASTER", false);
		config.set("CONTENT.BUILDER", false);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static String getRank(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getString("PLAYER.RANK");
	}
	
	public static String getRank(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getString("PLAYER.RANK");
	}

	public static void setRank(Player p, String rank) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("PLAYER.RANK", rank);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setLocation(Player p, Location loc) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LOCATION.X", loc.getX());
		config.set("LOCATION.Y", loc.getY());
		config.set("LOCATION.Z", loc.getZ());
		config.set("LOCATION.PITCH", loc.getPitch());
		config.set("LOCATION.YAW", loc.getYaw());
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static double getLocation(Player p, String parameter) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		if(parameter.equalsIgnoreCase("X")) {
			return config.getDouble("LOCATION.X");
		} else if(parameter.equalsIgnoreCase("Y")) {
			return config.getDouble("LOCATION.Y");
		} else if(parameter.equalsIgnoreCase("Z")) {
			return config.getDouble("LOCATION.Z");
		} else if(parameter.equalsIgnoreCase("Pitch")) {
			return config.getDouble("LOCATION.PITCH");
		} else if(parameter.equalsIgnoreCase("Yaw")) {
			return config.getDouble("LOCATION.YAW");
		} else {
			return 0;
		}
	}
	
	public static double getLocation(String uuid, String parameter) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		if(parameter.equalsIgnoreCase("X")) {
			return config.getDouble("LOCATION.X");
		} else if(parameter.equalsIgnoreCase("Y")) {
			return config.getDouble("LOCATION.Y");
		} else if(parameter.equalsIgnoreCase("Z")) {
			return config.getDouble("LOCATION.Z");
		} else if(parameter.equalsIgnoreCase("Pitch")) {
			return config.getDouble("LOCATION.PITCH");
		} else if(parameter.equalsIgnoreCase("Yaw")) {
			return config.getDouble("LOCATION.YAW");
		} else {
			return 0;
		}
	}

	public static ArrayList<ItemStack> getInventory(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return (ArrayList<ItemStack>) config.getList("PLAYER.INVENTORY");
	}
	
	public static ArrayList<ItemStack> getInventory(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return (ArrayList<ItemStack>) config.getList("PLAYER.INVENTORY");
	}

	public static void setInventory(Player p, ItemStack[] inventory_contents) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("PLAYER.INVENTORY", inventory_contents);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setInventory(String uuid, ItemStack[] inventory_contents) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("PLAYER.INVENTORY", inventory_contents);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setFriendStatus(Player p, boolean status) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("MISC.FRIEND", status);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setFriendStatus(String uuid, boolean status) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("MISC.FRIEND", status);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean getFriendStatus(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("MISC.FRIEND");
	}
	
	public static boolean getFriendStatus(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("MISC.FRIEND");
	}

	public static String getJoinDate(Player p, String parameter) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		if(parameter.equalsIgnoreCase("first")) {
			return config.getString("PLAYER.FIRST JOIN");
		} else if(parameter.equalsIgnoreCase("last")) {
			return config.getString("PLAYER.LAST JOIN");
		} else {
			return null;
		}
	}
	
	public static String getJoinDate(String uuid, String parameter) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		if(parameter.equalsIgnoreCase("first")) {
			return config.getString("PLAYER.FIRST JOIN");
		} else if(parameter.equalsIgnoreCase("last")) {
			return config.getString("PLAYER.LAST JOIN");
		} else {
			return null;
		}
	}

	public static void setJoinDate(Player p, String parameter, String date) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		String tobeplaceddate = date;
		
		if(date.equalsIgnoreCase("now")) {
			tobeplaceddate = df.format(dateobj);
		} 

		if(parameter.equalsIgnoreCase("first")) {
			config.set("PLAYER.FIRST JOIN", tobeplaceddate);
		} else if(parameter.equalsIgnoreCase("last")) {
			config.set("PLAYER.LAST JOIN", tobeplaceddate);
		} else {
			return;
		}
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setLevel(Player p, int level) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LEVEL.LEVEL", level);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setLevel(String uuid, int level) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LEVEL.LEVEL", level);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static int getLevel(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("LEVEL.LEVEL");
	}
	
	public static int getLevel(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("LEVEL.LEVEL");
	}
	
	public static float getEXP(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return (float) config.getDouble("LEVEL.EXP");
	}
	
	public static float getEXP(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return (float) config.getDouble("LEVEL.EXP");
	}

	public static void setEXP(Player p, float EXP) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LEVEL.EXP", EXP);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setEXP(String uuid, float EXP) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LEVEL.EXP", EXP);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getOnline(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("PLAYER.ONLINE");
	}
	
	public static boolean getOnline(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("PLAYER.ONLINE");
	}
	
	public static void setOnline(Player p, boolean statement) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("PLAYER.ONLINE", statement);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setCurrentName(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("PLAYER.CURRENT NAME", p.getName());
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrentName(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getString("PLAYER.CURRENT NAME");
	}
	
	public static String getCurrentName(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getString("PLAYER.CURRENT NAME");
	}
	
	public static boolean getGMStatus(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("CONTENT.GAMEMASTER");
	}
	
	public static boolean getGMStatus(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("CONTENT.GAMEMASTER");
	}
	
	public static void setGMStatus(Player p, boolean status) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("CONTENT.GAMEMASTER", status);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setGMStatus(String uuid, boolean status) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("CONTENT.GAMEMASTER", status);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getBuilderStatus(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("CONTENT.BUILDER");
	}
	
	public static boolean getBuilderStatus(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getBoolean("CONTENT.BUILDER");
	}
	
	public static void setBuilderStatus(Player p, boolean status) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("CONTENT.BUILDER", status);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setBuilderStatus(String uuid, boolean status) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("CONTENT.BUILDER", status);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getRawEXP(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("LEVEL.RAW_EXP");
	}
	
	public static int getRawEXP(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("LEVEL.RAW_EXP");
	}
	
	public static void setRawEXP(Player p, int amount) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LEVEL.RAW_EXP", amount);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setRawEXP(String uuid, int amount) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("LEVEL.RAW_EXP", amount);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setEXPpercent(Player p) {
		int level = PlayerFile.getLevel(p);
		int rawexp = PlayerFile.getRawEXP(p);
		int levelrawexp = Level.getLevelEXP(level+1);
		
		int rawexp_tocalc = rawexp - Level.getLevelEXP(level);
		int levelrawexp_tocalc = levelrawexp - Level.getLevelEXP(level);
		
		double percentdouble = (float) rawexp_tocalc / (float) levelrawexp_tocalc * 100;
		float percent = (float) (percentdouble / 100);
		
		if(percentdouble >= 100) {
			PlayerFile.setEXP(p, 0);
		} else {
			PlayerFile.setEXP(p, percent);
		}
	}
	
	public static int getHealth(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId().toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("HEALTH.CURRENT");
	}
	
	public static void setHealth(Player p, int health) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId().toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("HEALTH.CURRENT", health);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getMaxHealth(Player p) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId().toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("HEALTH.MAX");
	}
	
	public static void setMaxHealth(Player p, int health) {
		File playerfile = new File("plugins/Experimental/Players/", p.getUniqueId().toString()+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("HEALTH.MAX", health);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getHealth(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("HEALTH.CURRENT");
	}
	
	public static void setHealth(String uuid, int health) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("HEALTH.CURRENT", health);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getMaxHealth(String uuid) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		return config.getInt("HEALTH.MAX");
	}
	
	public static void setMaxHealth(String uuid, int health) {
		File playerfile = new File("plugins/Experimental/Players/", uuid+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(playerfile);
		
		config.set("HEALTH.MAX", health);
		
		try {
			config.save(playerfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
