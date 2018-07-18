package me.yagi.game.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class FlyCMD implements CommandExecutor {

	private boolean flymode = false, flymode_target = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
				if(args.length == 0) {
					flymode = p.getAllowFlight();
					if(flymode) {
						p.setAllowFlight(false);
						p.setFlying(false);

						p.sendMessage(Main.PREFIX+"§bYour flight mode was §3disabled§b!");
						Extras.playSound(p);
						flymode = false;
					} else {
						p.setAllowFlight(true);
						p.setFlying(true);

						p.sendMessage(Main.PREFIX+"§bYour flight mode was §3enabled§b!");
						Extras.playSound(p);
						flymode = true;
					}
				} else if(args.length == 1) {
					String target_name = args[0];
					Player target = Bukkit.getPlayer(target_name);
					if(target != null) {
						flymode_target = target.getAllowFlight();
						if(flymode_target) {
							target.setAllowFlight(false);
							target.setFlying(false);

							target.sendMessage(Main.PREFIX+"§bYour flight mode was §3disabled§b!");
							p.sendMessage(Main.PREFIX+"§bFlight mode of §3"+target.getName()+" §bhas been §3disabled§b!");
							Extras.playSound(p);
							Extras.playSound(target);
							flymode_target = false;
						} else {
							target.setAllowFlight(true);
							target.setFlying(true);

							target.sendMessage(Main.PREFIX+"§bYour flight mode was §3enabled§b!");
							p.sendMessage(Main.PREFIX+"§bFlight mode of §3"+target.getName()+" §bhas been §3enabled§b!");
							Extras.playSound(p);
							Extras.playSound(target);
							flymode_target = true;
						}

					} else {
						p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name+" §bis currently offline!");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Fly <Player>");
				}
			} else {
				Extras.noPerm(p);
			}
			
		} else {
			
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			
			if(args.length == 1) {
				String target_name = args[0];
				Player target = Bukkit.getPlayer(target_name);
				if(target != null) {
					if(flymode_target) {
						target.setAllowFlight(false);
						target.setFlying(false);
						
						target.sendMessage(Main.PREFIX+"§bYour flight mode was §3disabled§b!");
						p.sendMessage(Main.PREFIX+"§bFlight mode of §3"+target.getName()+" §bhas been §3disabled§b!");
						Extras.playSound(target);
						flymode_target = false;
					} else {
						target.setAllowFlight(true);
						target.setFlying(true);

						target.sendMessage(Main.PREFIX+"§bYour flight mode was §3enabled§b!");
						p.sendMessage(Main.PREFIX+"§bFlight mode of §3"+target.getName()+" §bhas been §3enabled§b!");
						Extras.playSound(target);
						flymode_target = true;
					}

				} else {
					p.sendMessage(Main.PREFIX+"§bThe player §3"+target_name+" §bis currently offline!");
				}
			} else {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/Fly <Player>");
			}
		}
		
		return false;
	}
	
}
