package me.yagi.game.commands.level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class SetEXPCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 2) {
					String target_name = args[0];
					float exp;
					try {
						exp = Float.valueOf(args[1]);
					} catch (Exception e) {
						p.sendMessage(Main.PREFIX+"§bThe given EXP-Amount has to be §3a float§b...");
						Extras.playSound(p, Sound.BLOCK_ANVIL_DESTROY);
						return false;
					}
					
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						 PlayerFile.setEXP(target, exp);
						 p.sendMessage(Main.PREFIX+"§3"+target.getName()+"'s §bEXP amount is now at §3"+exp+"§b!");
						 Extras.playSound(p);
					} else {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						String uuid = target_off.getUniqueId().toString();
						 PlayerFile.setEXP(uuid, exp);
						 p.sendMessage(Main.PREFIX+"§3"+target_name+"'s §bEXP amount is now at §3"+exp+"§b!");
						 Extras.playSound(p);
						
					}
					
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/SetEXP <Player> <EXP>");
				}
			} else {
				Extras.noPerm(p);
			}
			
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 2) {
				String target_name = args[0];
				float exp;
				try {
					exp = Float.valueOf(args[1]);
				} catch (Exception e) {
					p.sendMessage(Main.PREFIX+"§bThe given EXP-Amount has to be §3a float§b...");
					return false;
				}
				
				Player target = Bukkit.getPlayer(target_name);
				if(target != null) {
					 PlayerFile.setEXP(target, exp);
					 p.sendMessage(Main.PREFIX+"§3"+target.getName()+"'s §bEXP amount is now at §3"+exp+"§b!");
				} else {
					OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
					String uuid = target_off.getUniqueId().toString();
					 PlayerFile.setEXP(uuid, exp);
					 p.sendMessage(Main.PREFIX+"§3"+target_name+"'s §bEXP amount is now at §3"+exp+"§b!");
				}
				
			} else {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/SetEXP <Player> <EXP>");
			}
		}
		
		return false;
	}
	
}
