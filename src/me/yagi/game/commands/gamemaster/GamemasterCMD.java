package me.yagi.game.commands.gamemaster;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.ItemManager;
import me.yagi.game.api.PlayerFile;

public class GamemasterCMD implements CommandExecutor {

	private List<String> lore = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
		
			if(PlayerFile.getGMStatus(p)) {
				if(args.length == 0) {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Gamemaster <giveitem> <itemname>");
				} else if(args.length == 1) {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Gamemaster <giveitem> <itemname>");
				} else if(args.length == 2) {
					String arg0 = args[0];
					
					if(arg0.equalsIgnoreCase("giveitem") || arg0.equalsIgnoreCase("give")) {
						String itemname = args[1];
						try {
							ItemStack item = ItemManager.giveItem(itemname);
							p.getInventory().addItem(item);
						} catch (Exception e) {
							p.sendMessage("");
							p.sendMessage(Main.PREFIX+"§cThere is no item with the name "+itemname+"!");
							p.sendMessage(" §3§oNote: §b§oPlease make sure that you have written the name correctly.");
							return false;
						}
						
						p.sendMessage(Main.PREFIX+"§bThe item §3"+itemname+" §bhas been added to your inventory!");
						p.sendMessage(" §3§oNote: §b§oUse the GM Server for future-release items!");
					}
					
					else if(arg0.equalsIgnoreCase("givehorse")) {
						String horsename = args[1];
						if(horsename.equalsIgnoreCase("white")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§6Legendary Horse");
							lore.add("§8Requirements: §7Level 40");
							lore.add("§8Speed: §7Very Fast");
							ItemStack item = InventoryManager.createItem(329, "§6White Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §6White Horse §bhas been added to your inventory!");
							
						} else if(horsename.equalsIgnoreCase("black")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§2Unique Horse");
							lore.add("§8Requirements: §7Level 20");
							lore.add("§8Speed: §7Normal");
							ItemStack item = InventoryManager.createItem(329, "§2Black Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §2Black Horse §bhas been added to your inventory!");
							
						} else if(horsename.equalsIgnoreCase("brown")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§aCommon Horse");
							lore.add("§8Requirements: §7Level 10");
							lore.add("§8Speed: §7Slow");
							ItemStack item = InventoryManager.createItem(329, "§aBrown Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §aBrown Horse §bhas been added to your inventory!");
						} else if(horsename.equalsIgnoreCase("darkbrown")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§aCommon Horse");
							lore.add("§8Requirements: §7Level 15");
							lore.add("§8Speed: §7Slow");
							ItemStack item = InventoryManager.createItem(329, "§aDark-Brown Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §aDark-Brown Horse §bhas been added to your inventory!");
						} else if(horsename.equalsIgnoreCase("creamy")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§6Legendary Horse");
							lore.add("§8Requirements: §7Level 60");
							lore.add("§8Speed: §7Very Fast");
							ItemStack item = InventoryManager.createItem(329, "§6Creamy Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §6Creamy Horse §bhas been added to your inventory!");
						} else if(horsename.equalsIgnoreCase("whitefield")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§6Legendary Horse");
							lore.add("§8Requirements: §7Level 50");
							lore.add("§8Speed: §7Very Fast");
							ItemStack item = InventoryManager.createItem(329, "§6Whitefield Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §6Whitefield Horse §bhas been added to your inventory!");
						} else if(horsename.equalsIgnoreCase("chestnut")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§2Unique Horse");
							lore.add("§8Requirements: §7Level 25");
							lore.add("§8Speed: §7Fast");
							ItemStack item = InventoryManager.createItem(329, "§2Chestnut Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §2Chestnut Horse §bhas been added to your inventory!");
						} else if(horsename.equalsIgnoreCase("gray")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§cMythical Horse");
							lore.add("§8Requirements: §7Level 30");
							lore.add("§8Speed: §7Fast");
							ItemStack item = InventoryManager.createItem(329, "§cGray Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §cGray Horse §bhas been added to your inventory!");
						} else if(horsename.equalsIgnoreCase("blackfield")) {
							lore = new ArrayList<>();
							lore.add("§8§m----------------------");
							lore.add("§cMythical Horse");
							lore.add("§8Requirements: §7Level 35");
							lore.add("§8Speed: §7Fast");
							ItemStack item = InventoryManager.createItem(329, "§cBlackfield Horse", lore);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§bA §cBlackfield Horse §bhas been added to your inventory!");
						} else {
							p.sendMessage("");
							p.sendMessage(Main.PREFIX+"§cThere is no horse with the name "+horsename+"!");
							p.sendMessage(" §3§oNote: §aBROWN §aDARK-BROWN §2BLACK §2CHESTNUT §cGRAY §cBLACKFIELD §6WHITE §6WHITEFIELD §6CREAMY");
						}
						
						Extras.playSound(p);
					}
					
					else {
						p.sendMessage(Main.PREFIX+"§bUsage: §3/Gamemaster <giveitem/givehorse> <name>");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Gamemaster <giveitem/givehorse> <name>");
				}
			} else {
				Extras.noPerm(p);
			}
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			p.sendMessage(Main.PREFIX+"§bThis command can only be executed by §3a player§b!");
		}
		
		return false;
	}
	
}
