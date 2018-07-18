package me.yagi.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class FriendCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 2) {
					String target_name = args[1];
					Player target = Bukkit.getPlayer(target_name);
					if(target == null) {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						if(args[0].equalsIgnoreCase("status")) {
							if(PlayerFile.getFriendStatus(target_off.getUniqueId().toString())) {
								p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §ais §ba friend!");
							} else {
								p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §cis not §ba friend!");
							}
							
						} else {
							p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
						}
								
					} else {
						if(args[0].equalsIgnoreCase("status")) {
							if(PlayerFile.getFriendStatus(target)) {
								p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §ais §ba friend!");
							} else {
								p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §cis not §ba friend!");
							}
							
						} else {
							p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
						}
							
					}
					
				} else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("set")) {
						String target_name = args[1];
						Player target = Bukkit.getPlayer(target_name);
						if(target == null) {
							OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
							if(args[2].equalsIgnoreCase("on") || args[2].equalsIgnoreCase("true")) {
								PlayerFile.setFriendStatus(target_off.getUniqueId().toString(), false);
								p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §ais now §ba friend!");
							} else if(args[2].equalsIgnoreCase("off") || args[2].equalsIgnoreCase("false")) {
								PlayerFile.setFriendStatus(target_off.getUniqueId().toString(), true);
								p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §cis not §ba friend anymore!");
							} else {
								p.sendMessage(Main.PREFIX+"§cPlease enter a right parameter. §7[true/false]");
							}

						} else {
							if(args[2].equalsIgnoreCase("on") || args[2].equalsIgnoreCase("true")) {
								PlayerFile.setFriendStatus(target, false);
								p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §ais now §ba friend!");
							} else if(args[2].equalsIgnoreCase("off") || args[2].equalsIgnoreCase("false")) {
								PlayerFile.setFriendStatus(target, true);
								p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §cis not §ba friend anymore!");
							} else {
								p.sendMessage(Main.PREFIX+"§cPlease enter a right parameter. §7[true/false]");
							}
						}
					} else {
						p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
				}
			} else {
				Extras.noPerm(p);
				return false;
			}
			Extras.playSound(p);
			
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 2) {
				String target_name = args[1];
				Player target = Bukkit.getPlayer(target_name);
				if(target == null) {
					OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
					if(args[0].equalsIgnoreCase("status")) {
						if(PlayerFile.getFriendStatus(target_off.getUniqueId().toString())) {
							p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §ais §ba friend!");
						} else {
							p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §cis not §ba friend!");
						}
						
					} else {
						p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
					}
							
				} else {
					if(args[0].equalsIgnoreCase("status")) {
						if(PlayerFile.getFriendStatus(target)) {
							p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §ais §ba friend!");
						} else {
							p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §cis not §ba friend!");
						}
						
					} else {
						p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
					}
						
				}
				
			} else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("set")) {
					String target_name = args[1];
					Player target = Bukkit.getPlayer(target_name);
					if(target == null) {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						if(args[2].equalsIgnoreCase("on") || args[2].equalsIgnoreCase("true")) {
							PlayerFile.setFriendStatus(target_off.getUniqueId().toString(), false);
							p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §ais now §ba friend!");
						} else if(args[2].equalsIgnoreCase("off") || args[2].equalsIgnoreCase("false")) {
							PlayerFile.setFriendStatus(target_off.getUniqueId().toString(), true);
							p.sendMessage(Main.PREFIX+"§3"+target_off.getName()+" §cis not §ba friend anymore!");
						} else {
							p.sendMessage(Main.PREFIX+"§cPlease enter a right parameter. §7[true/false]");
						}

					} else {
						if(args[2].equalsIgnoreCase("on") || args[2].equalsIgnoreCase("true")) {
							PlayerFile.setFriendStatus(target, false);
							p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §ais now §ba friend!");
						} else if(args[2].equalsIgnoreCase("off") || args[2].equalsIgnoreCase("false")) {
							PlayerFile.setFriendStatus(target, true);
							p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §cis not §ba friend anymore!");
						} else {
							p.sendMessage(Main.PREFIX+"§cPlease enter a right parameter. §7[true/false]");
						}
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
				}
			} else {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/Friend <status/set> <player> <param>");
			}
		}
		
		return false;
	}
	
}
