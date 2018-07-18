package me.yagi.game.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.yagi.game.Main;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.Level;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.api.ServerProperties;

public class JoinQuit implements Listener {
	
	private List<String> motd = new ArrayList<>();
	private int join_task;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void on(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		List<String> whitelisted_players = ServerProperties.getWhitelistedPlayers();
		List<String> blacklisted_players = ServerProperties.getBlacklistedPlayers();
		
		if(ServerProperties.getBlacklistStatus()) {
			if(blacklisted_players.contains(p.getUniqueId().toString())) {
				p.kickPlayer("§6§lKingdom of Fire\n\n§eYou have been §6§lpermanently §eblacklisted from this server.\n§eIf you have any further questions, you can simply ask them on our discord or on our website!\n\n§6Discord: §ediscord.yzaimoglu.com\n§6Website: §edev.yzaimoglu.com");
				return;
			}
		}
		
		if(ServerProperties.getWhitelistStatus()) {
			if(!whitelisted_players.contains(p.getUniqueId().toString())) {
				p.kickPlayer("§6§lKingdom of Fire\n\n§eWe are currently undergoing maintenance. Please be patient, we will be online soon!\n§eIf you have any further questions, you can simply ask them on our discord or on our website!\n\n§6Discord: §ediscord.yzaimoglu.com\n§6Website: §edev.yzaimoglu.com");
				return;
			}
		}
		
		BossBar bar = Bukkit.createBossBar("§3Welcome back §b"+p.getName()+"§3!", BarColor.BLUE, BarStyle.SOLID);
		bar.addPlayer(p);
		bar.show();
		p.sendTitle("§3Welcome back", "§b"+p.getName());
		new BukkitRunnable() {
			
			@Override
			public void run() {
				bar.removeAll();
				bar.hide();
			}
		}.runTaskLater(Main.getMain(), 5*20);
		
		e.setJoinMessage(null);
		motd.add("");
		motd.add("");
		motd.add("§8§m---------------------------------------");
		motd.add("§3Welcome to this server §b"+p.getName()+"§3!");
		motd.add("§3Our Server is currently undergoing a recode");
		motd.add("§3but you can still look at the things!");
		motd.add("§3If you have §bany questions §3feel free");
		motd.add("§3to ask them by doing: §b/Ask");
		motd.add("§3Other help can simply be found here: §b/Help");
		motd.add("§8§m---------------------------------------");
		motd.add("");
		
		TabListener.setTablist(p);
		for(Player all : Bukkit.getOnlinePlayers()) {
			TabListener.setTablist(all);
			TabListener.updateTablist(all);
		}
		
		if(!PlayerFile.exists(p)) {
			PlayerFile.create(p);
		}
		if(!p.hasPlayedBefore()) {
			PlayerFile.setJoinDate(p, "first", "now");
		}
		
		if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
			p.setDisplayName("§6"+p.getName());
			p.setPlayerListName("§6"+p.getName());
		} else if(PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
			p.setDisplayName("§e"+p.getName());
			p.setPlayerListName("§e"+p.getName());
		} else if(PlayerFile.getBuilderStatus(p) || PlayerFile.getGMStatus(p)) {
			p.setDisplayName("§3"+p.getName());
			p.setPlayerListName("§3"+p.getName());
		} else {
			p.setDisplayName("§b"+p.getName());
			p.setPlayerListName("§b"+p.getName());
		}
		
		
		PlayerFile.setJoinDate(p, "last", "now");
		PlayerFile.setOnline(p, true);
		PlayerFile.setCurrentName(p);
		
			p.getInventory().clear();
			ItemStack[] contents = p.getInventory().getContents();
			List<?> list = PlayerFile.getInventory(p);
			
			for(int i = 0; i < list.size(); i++) {
				contents[i] = (ItemStack) list.get(i);
			}
			
			p.getInventory().setContents(contents);
		
		
		for(String all : motd) {
			p.sendMessage(all);
		}
		
		join_task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new BukkitRunnable() {
			
			@Override
			public void run() {

				ArmorEquip.checkForArmor(p);

				ActionBarAPI.sendActionBar(p, "§c❤ §c"+PlayerFile.getHealth(p)+"/"+PlayerFile.getMaxHealth(p)
				+"      §a✦§a "+PlayerFile.getRawEXP(p)+"/"+Level.getLevelEXP((PlayerFile.getLevel(p)+1))+"      "+PlayerFile.getTitle(p));
				
				int health = PlayerFile.getHealth(p);
				int maxhealth = PlayerFile.getMaxHealth(p);
				
				if(health >= maxhealth) {
					health = maxhealth;
					PlayerFile.setHealth(p, maxhealth);
					double percentage = (double) health / (double) maxhealth * 100;
					double fourty = 40;
					double hundred = 100;
					double i1 = fourty/hundred * percentage;
					p.setMaxHealth(40);
					p.setHealth(i1);
				} else {
					double regeneration_rate = 1.5;
					double health_to_be_added = (double) maxhealth / 100 * regeneration_rate;
					PlayerFile.setHealth(p, (int) (PlayerFile.getHealth(p)+health_to_be_added));
					double percentage = (double) health / (double) maxhealth * 100;
					double fourty = 40;
					double hundred = 100;
					double i1 = fourty/hundred * percentage;
					p.setMaxHealth(40);
					p.setHealth(i1);
				}
							
				PlayerFile.setEXPpercent(p);
				if(Level.checkForLevelUp(p)) {
					if(Level.reachedLevelCap(p)) {
						// Nothing
					} else {
						PlayerFile.setEXP(p, 0);
						PlayerFile.setLevel(p, PlayerFile.getLevel(p)+1);
						Level.levelUpMessage(p);
					}
				}
 				p.setLevel(PlayerFile.getLevel(p));
				p.setExp(PlayerFile.getEXP(p));
				
				
			}
		}, 0, 2*20);
		
		
		if(PlayerFile.getRank(p).equalsIgnoreCase("ADMINISTRATOR")) {
			Bukkit.broadcastMessage(" §8[§6Admin§8] §6"+p.getName()+" §3has logged in!");
			p.sendMessage("§7§oEnter Admin-Mode by executing §f/admin§7§o!");
		} else if(PlayerFile.getRank(p).equalsIgnoreCase("STAFF")) {
			Bukkit.broadcastMessage(" §8[§eStaff§8] §e"+p.getName()+" §3has logged in!");
			p.sendMessage("§7§oEnter Staff-Mode by executing §f/staff§7§o!");
		} 
		
	}
	
	@EventHandler
	public void on(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		if(ServerProperties.getWhitelistStatus()) {
			if(!ServerProperties.getWhitelistedPlayers().contains(p.getUniqueId().toString())) {
				return;
			}
		}

		PlayerFile.setLocation(p, p.getLocation());
		PlayerFile.setInventory(p, p.getInventory().getContents());
		PlayerFile.setOnline(p, false);
		
		Bukkit.getScheduler().cancelTask(join_task);
		
		if(PlayerFile.getRank(p).equalsIgnoreCase("ADMINISTRATOR")) {
			Bukkit.broadcastMessage(" §8[§6Admin§8] §6"+p.getName()+" §3has logged out!");
		} else if(PlayerFile.getRank(p).equalsIgnoreCase("STAFF")) {
			Bukkit.broadcastMessage(" §8[§eStaff§8] §e"+p.getName()+" §3has logged out!");
		} 
	}

}
