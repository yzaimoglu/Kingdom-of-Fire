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

public class TeleportCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
				if(args.length == 1) {
					String target_name = args[0];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						p.sendMessage(Main.PREFIX+"§bTeleporting to §3"+target.getName()+"§b...");
						p.teleport(target.getLocation());
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name+" §bis currently offline!");
					}
				} else if(args.length == 2) {
					String target_name1 = args[0];
					String target_name2 = args[1];
					Player target1 = Bukkit.getPlayer(target_name1);
					Player target2 = Bukkit.getPlayer(target_name2);
					if(target1 != null) {
						if(target2 != null) {
							target1.teleport(target2.getLocation());
							target1.sendMessage(Main.PREFIX+"§bTeleporting to §3"+target2.getName()+"§b...");
							target2.sendMessage(Main.PREFIX+"§bTeleporting §3"+target1.getName()+" §bto §3you§b...");
							p.sendMessage(Main.PREFIX+"§bTeleporting §3"+target1.getName()+" §bto §3you§b...");
							p.playSound(target1.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
							target1.playSound(target1.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
							target2.playSound(target2.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
						} else {
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name2+" §bis currently offline!");
						}
					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name1+" §bis currently offline!");
					}
				} else {
					p.sendMessage("§bUsage: §3/Teleport <Player> <Target>");
				}
			} else {
				Extras.noPerm(p);
			}
			
			
		} else {
			
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			
			if(args.length == 2) {
				String target_name1 = args[0];
				String target_name2 = args[1];
				Player target1 = Bukkit.getPlayer(target_name1);
				Player target2 = Bukkit.getPlayer(target_name2);
				if(target1 != null) {
					if(target2 != null) {
						target1.teleport(target2.getLocation());
						target1.sendMessage(Main.PREFIX+"§bTeleporting to §3"+target2.getName()+"§b...");
						target2.sendMessage(Main.PREFIX+"§bTeleporting §3"+target1.getName()+" §bto §3you§b...");
						p.sendMessage(Main.PREFIX+"§bTeleporting §3"+target1.getName()+" §bto §3you§b...");
						target1.playSound(target1.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
						target2.playSound(target2.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name2+" §bis currently offline!");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name1+" §bis currently offline!");
				}
			} else {
				p.sendMessage("§bUsage: §3/Teleport <Player> <Target>");
			}
		}
		
		return false;
	}
	
}
