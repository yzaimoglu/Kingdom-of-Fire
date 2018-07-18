package me.yagi.game.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class InventoryManager {

	public static Inventory createInventory(String title, int size, InventoryType type) {
		
		Inventory inv = Bukkit.createInventory(null, type, title);
		
		return inv;
	}
	
	public static Inventory createInventory(String title, int size) {
		
		Inventory inv = Bukkit.createInventory(null, size, title);
		
		return inv;
	}
	
	public static ItemStack createItem(int ID, String name) {
		
		ItemStack item = new ItemStack(ID);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		
		return item;
	}
	
	public static ItemStack createItem(int ID, String name, boolean unbreakable) {
		
		ItemStack item = new ItemStack(ID);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setUnbreakable(unbreakable);
		item.setItemMeta(im);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		
		return item;
	}
	
	public static ItemStack createItem(int ID, String name, List<String> lore, boolean unbreakable) {
		
		ItemStack item = new ItemStack(ID);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		im.setUnbreakable(unbreakable);
		item.setItemMeta(im);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		
		return item;
	}
	
	public static ItemStack createItem(Material material, Short subid, String name, List<String> lore) {
	      
	    ItemStack item = new ItemStack(material, 1, subid);
	    ItemMeta im = item.getItemMeta();
	    im.setDisplayName(name);
	    im.setLore(lore);
	    item.setItemMeta(im);
	      
	    return item;
	}
	
	public static ItemStack createItem(int id, Short subid, String name, List<String> lore) {
	      
	    ItemStack item = new ItemStack(id, 1, subid);
	    ItemMeta im = item.getItemMeta();
	    im.setDisplayName(name);
	    im.setLore(lore);
	    item.setItemMeta(im);
	      
	    return item;
	}
	
	public static ItemStack createItemWithMaterial(Material material, short i, int amount, String name, List<String> lore) {
	      
	    ItemStack item = new ItemStack(material, amount, i);
	    ItemMeta im = item.getItemMeta();
	    im.setDisplayName(name);
	    im.setLore(lore);
	    item.setItemMeta(im);
	      
	    return item;
	}
	
	public static ItemStack createItem(int id, Short subid, int amount, String name, List<String> lore) {
	      
	    ItemStack item = new ItemStack(id, amount, subid);
	    ItemMeta im = item.getItemMeta();
	    im.setDisplayName(name);
	    im.setLore(lore);
	    item.setItemMeta(im);
	      
	    return item;
	}
	
	public static ItemStack createItem(int ID, String name, List<String> lore) {
		
		ItemStack item = new ItemStack(ID);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		item.setItemMeta(im);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		
		return item;
	}
	
	public static ItemStack createSkull(String p, String name) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p);
		meta.setDisplayName(name);
		skull.setItemMeta(meta);
				
		return skull;
	}
	
}
