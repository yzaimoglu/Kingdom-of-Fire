package me.yagi.game.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.PlayerFile;

public class InventorySeeCMD implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
				if(args.length == 1) {
					String target_name = args[0];
					Player target_on = Bukkit.getPlayer(target_name);
					if(target_on != null) {
						Inventory inv = InventoryManager.createInventory("§3"+target_name+"'s Inventory", 36);
						inv.clear();
						p.sendMessage(Main.PREFIX+"§bOpening §3"+target_on.getName()+"'s §bInventory...");
						inv.setContents(target_on.getInventory().getStorageContents());
						p.openInventory(inv);
					} else {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						String uuid = target_off.getUniqueId().toString();
						
						p.sendMessage(Main.PREFIX+"§bOpening §3"+target_off.getName()+"'s §bInventory...");
						
						Inventory inv = InventoryManager.createInventory("§3"+target_name+"'s Inventory", 36);
						inv.clear();
						ItemStack[] contents = inv.getStorageContents();
						List<?> list = PlayerFile.getInventory(uuid);
						
						for(int i = 0; i < list.size(); i++) {
							contents[i] = (ItemStack) list.get(i);
						}
						
					    inv.setContents(contents);
					    
					    p.openInventory(inv);
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/InventorySee <Player>");
				}
			} else {
				Extras.noPerm(p);
			}
			
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			p.sendMessage(Main.PREFIX+"§bThis command can only be executed by a Player!");
		}
		
		return false;
	}
	
	@EventHandler
	public void on(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if(e.getInventory().getTitle().endsWith("'s Inventory")) {
			Inventory inv = e.getInventory();
			String target_name = inv.getName().replaceAll("§3", "").replaceAll("'s Inventory", "").replaceAll(" ", "");
			if(Bukkit.getPlayer(target_name) != null) {
				Player target_on = Bukkit.getPlayer(target_name);
				target_on.getInventory().setContents(inv.getContents());
			} else {
				String uuid = Bukkit.getOfflinePlayer(target_name).getUniqueId().toString();
				PlayerFile.setInventory(uuid, inv.getContents());
			}
			
		} else {
			return;
		}
	}
	
	

	
	
	
}
