package me.yagi.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class GamemodeCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 1) {
					String gamemode = args[0];
					if(gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("Creative")) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Creative§b!");
						Extras.playSound(p);
					} else if(gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("Survival")) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Survival§b!");
						Extras.playSound(p);
					} else if(gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("Adventure")) {
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Adventure§b!");
						Extras.playSound(p);
					} else if(gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("Spectator")) {
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Spectator§b!");
						Extras.playSound(p);
					} else {
						p.sendMessage(Main.PREFIX+"§bPlease provide one of the following gamemodes:");
						p.sendMessage(Main.PREFIX+"§3Survival Creative Adventure Spectator");
					}
				} else if(args.length == 2) {
					String gamemode = args[0];
					String target_name = args[1];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						if(gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("Creative")) {
							target.setGameMode(GameMode.CREATIVE);
							target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Creative§b!");
							p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Creative§b!");
							Extras.playSound(p);
							Extras.playSound(target);
						} else if(gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("Survival")) {
							target.setGameMode(GameMode.SURVIVAL);
							target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Survival§b!");
							p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Survival§b!");
							Extras.playSound(p);
							Extras.playSound(target);
						} else if(gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("Adventure")) {
							target.setGameMode(GameMode.ADVENTURE);
							target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Adventure§b!");
							p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Adventure§b!");
							Extras.playSound(p);
							Extras.playSound(target);
						} else if(gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("Spectator")) {
							target.setGameMode(GameMode.SPECTATOR);
							target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Spectator§b!");
							p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Spectator§b!");
							Extras.playSound(p);
							Extras.playSound(target);
						} else {
							p.sendMessage(Main.PREFIX+"§bPlease provide one of the following gamemodes:");
							p.sendMessage(Main.PREFIX+"§3Survival Creative Adventure Spectator");
						}
					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name+" §bis not online!");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Gamemode <Gamemode> <Player>");
				}
			} else {
				Extras.noPerm(p);
			}
			
			
		} else {
			
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 2) {
				String gamemode = args[0];
				String target_name = args[1];
				Player target = Bukkit.getPlayer(target_name);
				if(target != null) {
					if(gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("Creative")) {
						target.setGameMode(GameMode.CREATIVE);
						target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Creative§b!");
						p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Creative§b!");
						Extras.playSound(target);
					} else if(gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("Survival")) {
						target.setGameMode(GameMode.SURVIVAL);
						target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Survival§b!");
						p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Survival§b!");
						Extras.playSound(target);
					} else if(gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("Adventure")) {
						target.setGameMode(GameMode.ADVENTURE);
						target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Adventure§b!");
						p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Adventure§b!");
						Extras.playSound(target);
					} else if(gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("Spectator")) {
						target.setGameMode(GameMode.SPECTATOR);
						target.sendMessage(Main.PREFIX+"§bYour Gamemode has been changed to §3Spectator§b!");
						p.sendMessage(Main.PREFIX+"§bThe gamemode of §3"+target_name+" §bhas been changed to §3Spectator§b!");
						Extras.playSound(target);
					} else {
						p.sendMessage(Main.PREFIX+"§bPlease provide one of the following gamemodes:");
						p.sendMessage(Main.PREFIX+"§3Survival Creative Adventure Spectator");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name+" §bis not online!");
				}
			} else {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/Gamemode <Gamemode> <Player>");
			}
		}
		
		return false;
	}

	
	
}
