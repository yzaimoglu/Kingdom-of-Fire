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

public class PlayerInfoCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			 Player p = (Player) sender;
			 if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
				 if(args.length == 1) {
					 String target_name = args[0];
					 OfflinePlayer target = Bukkit.getOfflinePlayer(target_name);
					 String uuid = target.getUniqueId().toString();
					 p.sendMessage("");
					 p.sendMessage(Main.PREFIX+"§bPlayer Info: §3"+target.getName());
					 p.sendMessage(" §bCurrent Name: §3"+PlayerFile.getCurrentName(uuid));
					 if(PlayerFile.getRank(uuid).equalsIgnoreCase("Administrator")) {
						 p.sendMessage(" §bRank: §6Administrator");
					 } else if(PlayerFile.getRank(uuid).equalsIgnoreCase("Staff")) {
						 p.sendMessage(" §bRank: §4Staff");
					 } else {
						 p.sendMessage(" §bRank: §2Normal");
					 }
					 if(PlayerFile.getOnline(uuid)) {
						 p.sendMessage(" §bStatus: §aOnline");
					 } else {
						 p.sendMessage(" §bStatus: §cOffline");
					 }
					 p.sendMessage(" §bFirst Join: §3"+PlayerFile.getJoinDate(uuid, "first"));
					 p.sendMessage(" §bLast Join: §3"+PlayerFile.getJoinDate(uuid, "last"));
					 p.sendMessage(" §bLevel: §3"+PlayerFile.getLevel(uuid));
					 p.sendMessage(" §bExp: §3"+PlayerFile.getEXP(uuid)*100+"%");
					 p.sendMessage(" §bRaw EXP: §3"+PlayerFile.getRawEXP(uuid));
					 p.sendMessage(" §bLast Location: §3X: "+PlayerFile.getLocation(uuid, "X")+", Y: "+PlayerFile.getLocation(uuid, "Y")+", Z: "+PlayerFile.getLocation(uuid, "Z"));
					 p.sendMessage("");
					 if(PlayerFile.getFriendStatus(uuid)) {
						 p.sendMessage(" §bFriend: §aYes");
					 } else {
						 p.sendMessage(" §bFriend: §cNo");
					 }
					 if(PlayerFile.getGMStatus(uuid)) {
						 p.sendMessage(" §bGamemaster: §aYes");
					 } else {
						 p.sendMessage(" §bGamemaster: §cNo");
					 }
					 if(PlayerFile.getBuilderStatus(uuid)) {
						 p.sendMessage(" §bBuilder: §aYes");
					 } else {
						 p.sendMessage(" §bBuilder: §cNo");
					 }

					 
				 } else {
					 String target_name = p.getName();
					 OfflinePlayer target = Bukkit.getOfflinePlayer(target_name);
					 String uuid = target.getUniqueId().toString();
					 p.sendMessage("");
					 p.sendMessage(Main.PREFIX+"§bPlayer Info: §3"+target.getName());
					 p.sendMessage(" §bCurrent Name: §3"+PlayerFile.getCurrentName(uuid));
					 if(PlayerFile.getRank(uuid).equalsIgnoreCase("Administrator")) {
						 p.sendMessage(" §bRank: §6Administrator");
					 } else if(PlayerFile.getRank(uuid).equalsIgnoreCase("Staff")) {
						 p.sendMessage(" §bRank: §4Staff");
					 } else {
						 p.sendMessage(" §bRank: §2Normal");
					 }
					 if(PlayerFile.getOnline(uuid)) {
						 p.sendMessage(" §bStatus: §aOnline");
					 } else {
						 p.sendMessage(" §bStatus: §cOffline");
					 }
					 p.sendMessage(" §bFirst Join: §3"+PlayerFile.getJoinDate(uuid, "first"));
					 p.sendMessage(" §bLast Join: §3"+PlayerFile.getJoinDate(uuid, "last"));
					 p.sendMessage(" §bLevel: §3"+PlayerFile.getLevel(uuid));
					 p.sendMessage(" §bExp: §3"+PlayerFile.getEXP(uuid)*100+"%");
					 p.sendMessage(" §bRaw EXP: §3"+PlayerFile.getRawEXP(uuid));
					 p.sendMessage(" §bLast Location: §3X: "+PlayerFile.getLocation(uuid, "X")+", Y: "+PlayerFile.getLocation(uuid, "Y")+", Z: "+PlayerFile.getLocation(uuid, "Z"));
					 if(PlayerFile.getFriendStatus(uuid)) {
						 p.sendMessage(" §bFriend: §aYes");
					 } else {
						 p.sendMessage(" §bFriend: §cNo");
					 }
					 if(PlayerFile.getGMStatus(uuid)) {
						 p.sendMessage(" §bGamemaster: §aYes");
					 } else {
						 p.sendMessage(" §bGamemaster: §cNo");
					 }
					 if(PlayerFile.getBuilderStatus(uuid)) {
						 p.sendMessage(" §bBuilder: §aYes");
					 } else {
						 p.sendMessage(" §bBuilder: §cNo");
					 }
				 }
			 } else {
				 Extras.noPerm(p);
			 }
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 1) {
				 String target_name = args[0];
				 OfflinePlayer target = Bukkit.getOfflinePlayer(target_name);
				 String uuid = target.getUniqueId().toString();
				 p.sendMessage("");
				 p.sendMessage(Main.PREFIX+"§bPlayer Info: §3"+target.getName());
				 p.sendMessage(" §bCurrent Name: §3"+PlayerFile.getCurrentName(uuid));
				 if(PlayerFile.getRank(uuid).equalsIgnoreCase("Administrator")) {
					 p.sendMessage(" §bRank: §6Administrator");
				 } else if(PlayerFile.getRank(uuid).equalsIgnoreCase("Staff")) {
					 p.sendMessage(" §bRank: §4Staff");
				 } else {
					 p.sendMessage(" §bRank: §2Normal");
				 }
				 if(PlayerFile.getOnline(uuid)) {
					 p.sendMessage(" §bStatus: §aOnline");
				 } else {
					 p.sendMessage(" §bStatus: §cOffline");
				 }
				 p.sendMessage(" §bFirst Join: §3"+PlayerFile.getJoinDate(uuid, "first"));
				 p.sendMessage(" §bLast Join: §3"+PlayerFile.getJoinDate(uuid, "last"));
				 p.sendMessage(" §bLevel: §3"+PlayerFile.getLevel(uuid));
				 p.sendMessage(" §bExp: §3"+PlayerFile.getEXP(uuid)*100+"%");
				 p.sendMessage(" §bRaw EXP: §3"+PlayerFile.getRawEXP(uuid));
				 p.sendMessage(" §bLast Location: §3X: "+PlayerFile.getLocation(uuid, "X")+", Y: "+PlayerFile.getLocation(uuid, "Y")+", Z: "+PlayerFile.getLocation(uuid, "Z"));
				 if(PlayerFile.getFriendStatus(uuid)) {
					 p.sendMessage(" §bFriend: §aYes");
				 } else {
					 p.sendMessage(" §bFriend: §cNo");
				 }
				 if(PlayerFile.getGMStatus(uuid)) {
					 p.sendMessage(" §bGamemaster: §aYes");
				 } else {
					 p.sendMessage(" §bGamemaster: §cNo");
				 }
				 if(PlayerFile.getBuilderStatus(uuid)) {
					 p.sendMessage(" §bBuilder: §aYes");
				 } else {
					 p.sendMessage(" §bBuilder: §cNo");
				 }

				 
			 } else {
				 p.sendMessage(Main.PREFIX+"§bUsage: §3/PlayerInfo <Player>");
			 }
		}
		
		return false;
	}
	
}
