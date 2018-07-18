package me.yagi.game.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.yagi.game.api.InventoryManager;

public class TestCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			ItemStack item = InventoryManager.createItem(329, "§bWhite Horse");
			Player p = (Player) sender;
			p.getInventory().addItem(item);
			p.sendMessage("White Horse");
		} else {
			return false;
		}
		
		return false;
	}
	
}
