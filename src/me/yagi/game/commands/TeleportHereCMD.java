package me.yagi.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class TeleportHereCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
				if(args.length == 1) {
					String target_name = args[0];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						target.teleport(p.getLocation());
						p.sendMessage(Main.PREFIX+"§bTeleporting §3"+target.getName()+" §bto §3you§b...");
						target.sendMessage(Main.PREFIX+"§3"+p.getName()+" §bhas teleported to §3you§b!");
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
						target.playSound(target.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name+" §bis currently offline!");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/TeleportHere <Player>");
				}
			} else {
				Extras.noPerm(p);
			}
			
		} else {
			Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis command can only be executed as a Player!");
		}
		
		return false;
	}
	
}
