package me.yagi.game.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChatExtras {

	private static List<String> blockedwords = new ArrayList<>();
	private static List<String> blockedcmds = new ArrayList<>();
	private static List<String> domainendungen = new ArrayList<>();

	public static void createBlockedWordsFile() {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedwords.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(wordsfile);
		 
		 blockedwords.add("Hurensohn");
		 config.set("WORDS", blockedwords);
		 
		 try {
			 config.save(wordsfile);
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static boolean checkForBlockedWordsFile() {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedwords.yml");
		 
		 if(wordsfile.exists()) {
			 return true;
		 } else {
			 return false;
		 }
	}
	
	public static void createBlockedCommandsFile() {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedcmds.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(wordsfile);
		 
		 blockedcmds.add("/plugins");
		 config.set("COMMANDS", blockedcmds);
		 
		 try {
			 config.save(wordsfile);
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static boolean checkForBlockedCommandsFile() {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedcmds.yml");
		 
		 if(wordsfile.exists()) {
			 return true;
		 } else {
			 return false;
		 }
	}
	
	public static void setBlockedCommandsFile(List<String> blocked) {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedcmds.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(wordsfile);
		 
		 config.set("COMMANDS", blocked);
		 
		 try {
			 config.save(wordsfile);
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static List<String> getBlockedCommands() {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedcmds.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(wordsfile);
		 
		 return config.getStringList("COMMANDS");
	}
	
	public static void setBlockedWordsFile(List<String> blocked) {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedwords.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(wordsfile);
		 
		 config.set("WORDS", blocked);
		 
		 try {
			 config.save(wordsfile);
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static List<String> getBlockedWords() {
		 File wordsfile = new File("plugins/Experimental/Others/", "blockedwords.yml");
		 FileConfiguration config = YamlConfiguration.loadConfiguration(wordsfile);
		 
		 return config.getStringList("WORDS");
	}
	
	public static boolean checkForBlockedWords(String message) {
		message = message.toLowerCase();
		blockedwords = getBlockedWords();
		for(String word : blockedwords) {
			if(message.contains(word.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkForBlockedCmds(String cmd) {
		blockedcmds = getBlockedWords();
		for(String command : blockedcmds) {
			if(cmd.startsWith("/"+command)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkForAdvertisement(String message) {
		
		domainendungen.add(".de");
		domainendungen.add(".com");
		domainendungen.add(".com.tr");
		domainendungen.add(".me");
		domainendungen.add(".at");
		domainendungen.add(".tk");
		domainendungen.add(".ml");
		domainendungen.add(".co.uk");
		domainendungen.add(".net");
		
		for(String domain : domainendungen) {
			if(message.contains(domain)) {
				return true;
			}
		}
		return false;
	}
}
