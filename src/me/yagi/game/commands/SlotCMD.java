package me.yagi.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.api.ServerProperties;

public class SlotCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 1) {
					int new_slots = 0;
					try {
						new_slots = Integer.valueOf(args[0]);
					} catch (Exception e) {
						p.sendMessage(Main.PREFIX+"§bThe slot amount to be limited has to be §3an integer§b!");
						p.sendMessage(" §bExample: §360");
						Extras.playSound(p, Sound.BLOCK_ANVIL_BREAK);
						return false;
					}
					
					ServerProperties.setSlots(new_slots);
					p.sendMessage(Main.PREFIX+"§bThe slot amount has been limited to §3"+new_slots+"§b!");
					Extras.playSound(p);
				} else {
					p.sendMessage(Main.PREFIX+"§bThe current slot amount is limited to:");
					p.sendMessage(" §3"+ServerProperties.getSlots());
					Extras.playSound(p);
				}
			} else {
				Extras.noPerm(p);
			}
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 1) {
				int new_slots = 0;
				try {
					new_slots = Integer.valueOf(args[0]);
				} catch (Exception e) {
					p.sendMessage(Main.PREFIX+"§bThe slot amount to be limited has to be §3an integer§b!");
					p.sendMessage(" §bExample: §360");
					return false;
				}
				
				ServerProperties.setSlots(new_slots);
				p.sendMessage(Main.PREFIX+"§bThe slot amount has been limited to §3"+new_slots+"§b!");
			} else {
				p.sendMessage(Main.PREFIX+"§bThe current slot amount is limited to:");
				p.sendMessage(" §3"+ServerProperties.getSlots());
			}
		}
		
		
		return false;
	}
	
}
