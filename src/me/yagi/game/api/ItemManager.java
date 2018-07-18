package me.yagi.game.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	private static List<String> lore = new ArrayList<>();
	private static List<ItemStack> availableitems = new ArrayList<>();
	
	public static void createTestItemFile() {
		File file = new File("plugins/Experimental/Items/", "Template_new.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		lore.add("§1This is a powerful weapon!");
		lore.add("§2This is very powerful.");
		lore.add("§3Test 1");
		
		config.set("ID", 5);
		config.set("Type", "Weapon");
		config.set("Level Requirement", 40);
		config.set("Damage Min", 100);
		config.set("Damage Max", 150);
		config.set("Lore", lore);
		
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static ItemStack giveItem(String name) {
		
		File file = new File("plugins/Experimental/Items/", name+".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		ItemStack item = InventoryManager.createItem(config.getInt("ID"), name);
		
		ItemMeta im = item.getItemMeta();
		if(config.getString("Tier").equalsIgnoreCase("Legendary")) {
			im.setDisplayName("§6"+name);
		} else if(config.getString("Tier").equalsIgnoreCase("Mythical")) {
			im.setDisplayName("§c"+name);
		} else if(config.getString("Tier").equalsIgnoreCase("Unique")) {
			im.setDisplayName("§2"+name);
		} else if(config.getString("Tier").equalsIgnoreCase("Common")) {
			im.setDisplayName("§a"+name);
		}
		im.setLore(config.getStringList("Lore"));
		im.setUnbreakable(true);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		
		item.setItemMeta(im);
		
		if(ServerProperties.getItemList().contains(name)) {
			return item;
		} else {
			return InventoryManager.createItem(1, "§cThere is no such item!"); 
		}
		
		
	}
	
	public static List<ItemStack> getAvailableItems() {
		
		availableitems.add(giveItem("Ethereal")); // Legendary - Ethereal - Level 40 - Damage 100-150
		
		return availableitems;
		
	}
}
