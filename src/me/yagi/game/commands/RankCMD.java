package me.yagi.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class RankCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 0) {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Rank <Player> <Rank>");
				} else if(args.length == 1) {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Rank "+args[0]+ " <Rank>");
				} else if(args.length == 2) {
					String target_name = args[0];
					String rank = args[1];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						if(rank.equalsIgnoreCase("Administrator") || rank.equalsIgnoreCase("Admin")) {
							PlayerFile.setRank(target, "ADMINISTRATOR");
							p.sendMessage(Main.PREFIX+"§bThe rank of §3"+target.getName()+" §bhas been successfully set to §6Administrator§b!");
							Extras.playSound(p);
							target.kickPlayer("§6§lKingdom of Fire\n\n§eCongratulations!\n§eYour rank has been updated by an §6Administrator§e!\n§eYour new rank is §6Administrator§e!");
						} else if(rank.equalsIgnoreCase("Staff")) {
							PlayerFile.setRank(target, "STAFF");
							p.sendMessage(Main.PREFIX+"§bThe rank of §3"+target.getName()+" §bhas been successfully set to §eStaff§b!");
							Extras.playSound(p);
							target.kickPlayer("§6§lKingdom of Fire\n\n§eCongratulations!\n§eYour rank has been updated by an §6Administrator§e!\n§eYour new rank is §eStaff§e!");
						} else if(rank.equalsIgnoreCase("Normal")) {
							PlayerFile.setRank(target, "NORMAL");
							p.sendMessage(Main.PREFIX+"§bThe rank of §3"+target.getName()+" §bhas been successfully set to §2Normal§b!");
							Extras.playSound(p);
							target.kickPlayer("§6§lKingdom of Fire\n\n§eCongratulations!\n§eYour rank has been updated by an §6Administrator§e!\n§eYour new rank is §bNormal§e!");
						} else {
							p.sendMessage(Main.PREFIX+"§bPlease provide one of the following ranks:");
							p.sendMessage(" §6Administrator §eStaff §2Normal");
						}
					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+ target_name +" is not online!");
					}
				}
			} else {
				Extras.noPerm(p);
			}
			
			
		} else {
			
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 0) {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/Rank <Player> <Rank>");
			} else if(args.length == 1) {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/Rank "+args[0]+ " <Rank>");
			} else if(args.length == 2) {
				String target_name = args[0];
				String rank = args[1];
				Player target = Bukkit.getPlayer(target_name);
				if(target != null) {
					if(rank.equalsIgnoreCase("Administrator") || rank.equalsIgnoreCase("Admin")) {
						PlayerFile.setRank(target, "ADMINISTRATOR");
						p.sendMessage(Main.PREFIX+"§bThe rank of §3"+target.getName()+" §bhas been successfully set to §6Administrator§b!");
						target.kickPlayer("§6§lKingdom of Fire\n\n§eCongratulations!\n§eYour rank has been updated by an §6Administrator§e!\n§eYour new rank is §6Administrator§e!");
					} else if(rank.equalsIgnoreCase("Staff")) {
						PlayerFile.setRank(target, "STAFF");
						p.sendMessage(Main.PREFIX+"§bThe rank of §3"+target.getName()+" §bhas been successfully set to §eStaff§b!");
						target.kickPlayer("§6§lKingdom of Fire\n\n§eCongratulations!\n§eYour rank has been updated by an §6Administrator§e!\n§eYour new rank is §eStaff§e!");
					} else if(rank.equalsIgnoreCase("Normal")) {
						PlayerFile.setRank(target, "NORMAL");
						p.sendMessage(Main.PREFIX+"§bThe rank of §3"+target.getName()+" §bhas been successfully set to §2Normal§b!");
						target.kickPlayer("§6§lKingdom of Fire\n\n§eCongratulations!\n§eYour rank has been updated by an §6Administrator§e!\n§eYour new rank is §2Normal§e!");
					} else {
						p.sendMessage(Main.PREFIX+"§bPlease provide one of the following ranks:");
						p.sendMessage(" §6Administrator §eStaff §2Normal");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bThe player §3"+ target_name +" is not online!");
				}
			}
		}
		
		return false;
	}
	
}
