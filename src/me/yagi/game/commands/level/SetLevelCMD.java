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

public class SetLevelCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 2) {
					String target_name = args[0];
					int level;
					try {
						level = Integer.valueOf(args[1]);
					} catch (Exception e) {
						p.sendMessage(Main.PREFIX+"§bThe level amount has to be §3an integer§b...");
						Extras.playSound(p, Sound.BLOCK_ANVIL_BREAK);
						return false;
					}
					
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						PlayerFile.setLevel(target, level);
						p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §bis now §3level "+level+"§b!");
						Extras.playSound(p);
					} else {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						String uuid = target_off.getUniqueId().toString();
						PlayerFile.setLevel(uuid, level);
						p.sendMessage(Main.PREFIX+"§3"+target_name+" §bis now §3level "+level+"§b!");
						Extras.playSound(p);
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/SetLevel <Player> <Level>");
				}
			} else {
				Extras.noPerm(p);
			}
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 2) {
				String target_name = args[0];
				int level;
				try {
					level = Integer.valueOf(args[1]);
				} catch (Exception e) {
					p.sendMessage(Main.PREFIX+"§bThe level amount has to be §3an integer§b...");
					return false;
				}
				
				Player target = Bukkit.getPlayer(target_name);
				if(target != null) {
					PlayerFile.setLevel(target, level);
					p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §bis now §3level "+level+"§b!");
				} else {
					OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
					String uuid = target_off.getUniqueId().toString();
					PlayerFile.setLevel(uuid, level);
					p.sendMessage(Main.PREFIX+"§3"+target.getName()+" §bis now §3level "+level+"§b!");
				}
			} else {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/SetLevel <Player> <Level>");
			}
		}
		
		return false;
	}

}
