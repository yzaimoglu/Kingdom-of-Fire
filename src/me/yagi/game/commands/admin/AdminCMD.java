package me.yagi.game.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class AdminCMD implements CommandExecutor, Listener {

	public static List<String> leer = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				
				AdminInventories.MainMenu.open(p);
				
			} else {
				Extras.noPerm(p);
			}
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			p.sendMessage(Main.PREFIX+"§bThis command is §3Administrator-use only§b!");
			p.sendMessage(" §bYou can use §3/console§b!");
		}
		
		return false;
	}
	
}
