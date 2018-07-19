package me.yagi.game.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.yagi.game.Main;
import me.yagi.game.api.ChatExtras;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.api.ServerProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Chat implements Listener {
	
	@EventHandler
	public void on(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String message = e.getMessage();
		message = message.replaceAll("%", "%%");
		String format = " §7[%level%/%title%] §b%player%§8: §7%message%";
		e.setCancelled(true);
		
		ServerProperties.setNewMessage(p, message);

		if(PlayerFile.getRank(p).equalsIgnoreCase("ADMINISTRATOR") || PlayerFile.getRank(p).equalsIgnoreCase("STAFF")) {
			if(message.startsWith("!")) {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(PlayerFile.getRank(all).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
						String rankprefix = "§8[§eStaff§8] §e";
						if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
							rankprefix = "§8[§6Admin§8] §6";
						}
						all.sendMessage(Main.PREFIX+"§8[§eSTAFF CHAT§8] "+rankprefix+p.getName()+"§7: §e"+message.replaceFirst("!", ""));
						Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§e[STAFF CHAT] §6"+p.getName()+"§7: §e"+message.replaceFirst("!", ""));
						return;
					}
				}
			}
		}
		
		if(ChatExtras.checkForBlockedWords(message)) {
			p.sendMessage(Main.PREFIX+"§cPlease watch the words you use!");
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(PlayerFile.getRank(p).equalsIgnoreCase("ADMINISTRATOR") || PlayerFile.getRank(p).equalsIgnoreCase("STAFF")) {
					all.sendMessage("");
					all.sendMessage(Main.PREFIX+"§3The player §b"+p.getName()+" §3has used a blocked word!");
					all.sendMessage(" §3Message: §b"+message);
				}
			}
			return;
		} else if(ChatExtras.checkForAdvertisement(message)) {
			p.sendMessage(Main.PREFIX+"§cPlease do not advertise for other servers!");
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(PlayerFile.getRank(p).equalsIgnoreCase("ADMINISTRATOR") || PlayerFile.getRank(p).equalsIgnoreCase("STAFF")) {
					all.sendMessage("");
					all.sendMessage(Main.PREFIX+"§3The player §b"+p.getName()+" §3has advertised for another server!");
					all.sendMessage(" §3Message: §b"+message);
				}
			}
			return;
		} else {
			
			if(PlayerFile.getRank(p).equalsIgnoreCase("ADMINISTRATOR")) {
				format = " §7[%level%/%title%] §8[§6Administrator§8] §6%player%§8: §e%message%";
				format = format.replaceAll("%player%", p.getName());
				format = format.replaceAll("%message%", message.replaceAll("&", "§"));
				format = format.replaceAll("%level%", String.valueOf(PlayerFile.getLevel(p)));
				
				if(PlayerFile.getGMStatus(p)) {
					format = format.replaceAll("%title%", "GM");
				} else if(PlayerFile.getBuilderStatus(p)) {
					format = format.replaceAll("%title%", "Build");
				} else {
					if(PlayerFile.getLevel(p) <= 4) {
						format = format.replaceAll("%title%", "Trainee");
					} else if(PlayerFile.getLevel(p) <= 14) {
						format = format.replaceAll("%title%", "Squire");
					} else if(PlayerFile.getLevel(p) <= 29) {
						format = format.replaceAll("%title%", "Knight");
					} else if(PlayerFile.getLevel(p) <= 39) {
						format = format.replaceAll("%title%", "Baron");
					} else if(PlayerFile.getLevel(p) <= 54) {
						format = format.replaceAll("%title%", "Lord");
					} else if(PlayerFile.getLevel(p) <= 60) {
						format = format.replaceAll("%title%", "Duke");
					} else {
						format = format.replaceAll("%title%", "King");
					}
				}
				Bukkit.getConsoleSender().sendMessage(format);
				Location loc = p.getLocation();
				Entity[] entitylist = getNearbyEntities(loc, 25);
				for(Entity entity : entitylist) {
				    if(entity instanceof Player) {
				        Player target = (Player) entity;
				        target.sendMessage(format);
                    }
                }
			}
			
			else if(PlayerFile.getRank(p).equalsIgnoreCase("STAFF")) {
				
				format = " §7[%level%/%title%] §8[§eStaff§8] §e%player%§8: §7%message%";
				format = format.replaceAll("%player%", p.getName());
				format = format.replaceAll("%message%", message.replaceAll("&", "§"));
				format = format.replaceAll("%level%", String.valueOf(PlayerFile.getLevel(p)));
				
				if(PlayerFile.getGMStatus(p)) {
					format = format.replaceAll("%title%", "GM");
				} else if(PlayerFile.getBuilderStatus(p)) {
					format = format.replaceAll("%title%", "Build");
				} else {
					if(PlayerFile.getLevel(p) <= 4) {
						format = format.replaceAll("%title%", "Trainee");
					} else if(PlayerFile.getLevel(p) <= 14) {
						format = format.replaceAll("%title%", "Squire");
					} else if(PlayerFile.getLevel(p) <= 29) {
						format = format.replaceAll("%title%", "Knight");
					} else if(PlayerFile.getLevel(p) <= 39) {
						format = format.replaceAll("%title%", "Baron");
					} else if(PlayerFile.getLevel(p) <= 54) {
						format = format.replaceAll("%title%", "Lord");
					} else if(PlayerFile.getLevel(p) <= 60) {
						format = format.replaceAll("%title%", "Duke");
					} else {
						format = format.replaceAll("%title%", "King");
					}
				}
				
				Bukkit.getConsoleSender().sendMessage(format);
				Location loc = p.getLocation();
                Entity[] entitylist = getNearbyEntities(loc, 25);
                for(Entity entity : entitylist) {
                    if(entity instanceof Player) {
                        Player target = (Player) entity;
                        target.sendMessage(format);
                    }
                }
				
			}
			
			else if(PlayerFile.getRank(p).equalsIgnoreCase("NORMAL")) {
				
				format = format.replaceAll("%player%", p.getName());
				format = format.replaceAll("%message%", message);
				format = format.replaceAll("%level%", String.valueOf(PlayerFile.getLevel(p)));
				
				if(PlayerFile.getGMStatus(p)) {
					format = format.replaceAll("%title%", "GM");
				} else if(PlayerFile.getBuilderStatus(p)) {
					format = format.replaceAll("%title%", "Build");
				} else {
					if(PlayerFile.getLevel(p) <= 4) {
						format = format.replaceAll("%title%", "Trainee");
					} else if(PlayerFile.getLevel(p) <= 14) {
						format = format.replaceAll("%title%", "Squire");
					} else if(PlayerFile.getLevel(p) <= 29) {
						format = format.replaceAll("%title%", "Knight");
					} else if(PlayerFile.getLevel(p) <= 39) {
						format = format.replaceAll("%title%", "Baron");
					} else if(PlayerFile.getLevel(p) <= 54) {
						format = format.replaceAll("%title%", "Lord");
					} else if(PlayerFile.getLevel(p) <= 60) {
						format = format.replaceAll("%title%", "Duke");
					} else {
						format = format.replaceAll("%title%", "King");
					}
				}
				
				Bukkit.getConsoleSender().sendMessage(format);
                Location loc = p.getLocation();
                Entity[] entitylist = getNearbyEntities(loc, 25);
                for(Entity entity : entitylist) {
                    if(entity instanceof Player) {
                        Player target = (Player) entity;
                        target.sendMessage(format);
                    }
                }
				
			} else {
				return;
			}
			Extras.playSound(p);
		}
		
	}

    private static Entity[] getNearbyEntities(Location l, int radius){
        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16))/16;
        HashSet<Entity> radiusEntities = new HashSet<Entity>();
        for (int chX = 0 -chunkRadius; chX <= chunkRadius; chX ++){
            for (int chZ = 0 -chunkRadius; chZ <= chunkRadius; chZ++){
                int x=(int) l.getX(),y=(int) l.getY(),z=(int) l.getZ();
                for (Entity e : new Location(l.getWorld(),x+(chX*16),y,z+(chZ*16)).getChunk().getEntities()){
                    if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) radiusEntities.add(e);
                }
            }
        }
        return radiusEntities.toArray(new Entity[radiusEntities.size()]);
    }

	private static void sendMessage(Player p, String message) {

		p.sendMessage(message);

	}
	

}
