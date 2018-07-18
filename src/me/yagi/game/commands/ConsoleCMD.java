package me.yagi.game.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import me.yagi.game.api.ServerProperties;

public class ConsoleCMD implements CommandExecutor {
	
	private static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private static Date dateobj = new Date();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				p.sendMessage(Main.PREFIX+"§bThis command can only be run by §3the console§b...");
				p.sendMessage(" §bYou can use §3/admin §binstead!");
			} else {
				Extras.noPerm(p);
			}
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("time")) {
					p.sendMessage(Main.PREFIX+"§bCurrent Time:");
					p.sendMessage(" §3"+df.format(dateobj));
				} else if(args[0].equalsIgnoreCase("help")) {
					p.sendMessage("");
					p.sendMessage(Main.PREFIX+"§bConsole Command Help:");
					p.sendMessage(" §3/Console Time");
					p.sendMessage(" §3/Console <OP/deOP> <Player>");
					p.sendMessage(" §3/Console Slots <Amount>");
					p.sendMessage(" §3/Console Maintenance <true/false>");
					p.sendMessage(" §3/Console Whitelist <Player>");
					p.sendMessage(" §3/Console Blacklist <Player>");
					p.sendMessage(" §3/Console Content <Player> <Builder/GM> <true/false>");
					p.sendMessage("");
				} else if(args[0].equalsIgnoreCase("slots") || args[0].equalsIgnoreCase("slot")) {
					p.sendMessage(Main.PREFIX+"§bThe current slot amount is limited to:");
					p.sendMessage(" §3"+ServerProperties.getSlots());
				} else {
					p.sendMessage("§bUsage: §3/Console help");
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("op")) {
					String target_name = args[1];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						if(target.isOp()) {
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target.getName()+" §bis already an §3Operator§b!");
						} else {
							target.setOp(true);
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target.getName()+" §bis now an §3Operator§b!");
						}

					} else {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						if(target_off.isOp()) {
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target_off.getName()+" §bis already an §3Operator§b!");
						} else {
							target_off.setOp(true);
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target_off.getName()+" §bis now an §3Operator§b!");
						}
					}
			
				} else if(args[0].equalsIgnoreCase("deop")) {
					String target_name = args[1];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						if(!target.isOp()) {
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target.getName()+" §bis not an §3Operator§b!");
						} else {
							target.setOp(false);
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target.getName()+" §bis now an §3Operator §banymore!");
						}

					} else {
						OfflinePlayer target_off = Bukkit.getOfflinePlayer(target_name);
						if(!target_off.isOp()) {
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target_off.getName()+" §bis not an §3Operator§b!");
						} else {
							target_off.setOp(false);
							p.sendMessage(Main.PREFIX+"§bThe player §3"+target_off.getName()+" §bis not an §3Operator §banymore!");
						}
					}
				} else if(args[0].equalsIgnoreCase("slots") || args[0].equalsIgnoreCase("slot")) {
					int slot = 0;
					try {
						slot = Integer.valueOf(args[1]);
					} catch (Exception e) {
						p.sendMessage(Main.PREFIX+"§bThe slot amount to be limited has to be §3an integer§b!");
						p.sendMessage(" §bExample: §360");
						return false;
					}
					ServerProperties.setSlots(slot);
					p.sendMessage(Main.PREFIX+"§bThe slot amount has been limited to §3"+slot+"§b!");
				} else if(args[0].equalsIgnoreCase("whitelist")) {
					String name = args[1];
					OfflinePlayer whitelistplayer = Bukkit.getOfflinePlayer(name);
					String uuid = whitelistplayer.getUniqueId().toString();
					List<String> whitelistedplayers = ServerProperties.getWhitelistedPlayers();
					if(whitelistedplayers.contains(uuid)) {
						ServerProperties.removeWhitelistedPlayers(uuid);
						p.sendMessage(Main.PREFIX+"§bThe player §3"+name+" §bhas been removed from the whitelist!");
					} else {
						ServerProperties.addWhitelistedPlayer(uuid);
						p.sendMessage(Main.PREFIX+"§bThe player §3"+name+" §bhas been added to the whitelist!");
					}
				} else if(args[0].equalsIgnoreCase("blacklist")) {
					String name = args[1];
					OfflinePlayer blacklistplayer = Bukkit.getOfflinePlayer(name);
					String uuid = blacklistplayer.getUniqueId().toString();
					List<String> blacklistedplayers = ServerProperties.getBlacklistedPlayers();
					if(blacklistedplayers.contains(uuid)) {
						ServerProperties.removeBlacklistedPlayer(uuid);
						p.sendMessage(Main.PREFIX+"§bThe player §3"+name+" §bhas been removed from the blacklist!");
					} else {
						ServerProperties.addBlacklistedPlayer(uuid);
						p.sendMessage(Main.PREFIX+"§bThe player §3"+name+" §bhas been added to the blacklist!");
					}
				}
				else if(args[0].equalsIgnoreCase("maintenance")) {
					boolean status = Boolean.valueOf(args[1]);
					ServerProperties.setWhitelistStatus(status);
					p.sendMessage(Main.PREFIX+"§bThe whitelist has been toggled, the status is now §3"+status+"§b!");
				}
				else {
					p.sendMessage("§bUsage: §3/Console help");
				}
			} else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("Content")) {
					String name = args[1];
					OfflinePlayer contentplayer = Bukkit.getOfflinePlayer(name);
					String uuid = contentplayer.getUniqueId().toString();
					String contenttype = args[2];
					boolean status = false;
					try {
						status = Boolean.valueOf(args[3]);
					} catch (Exception e) {
						p.sendMessage(Main.PREFIX+"§bThe status must be a boolean! §3[true/false]");
					}
					if(contenttype.equalsIgnoreCase("Builder")) {
						PlayerFile.setBuilderStatus(uuid, status);
						p.sendMessage(Main.PREFIX+"§3"+PlayerFile.getCurrentName(uuid)+"§b's Builder Status has been set to §3"+status+"§b!");
						
					} else if(contenttype.equalsIgnoreCase("GM")) {
						PlayerFile.setGMStatus(uuid, status);
						p.sendMessage(Main.PREFIX+"§3"+PlayerFile.getCurrentName(uuid)+"§b's GM Status has been set to §3"+status+"§b!");
					} else {
						p.sendMessage(Main.PREFIX+"§bThe content type has to be either §3Builder §bor §3GM§b!");
					}
					
				} else {
					p.sendMessage("§bUsage: §3/Console help");
				}
			}
			else {
				p.sendMessage("§bUsage: §3/Console help");
			}
		}
		
		return false;
	}
	
}
