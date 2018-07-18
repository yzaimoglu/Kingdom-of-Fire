package me.yagi.game.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import me.yagi.game.Main;
import me.yagi.game.api.ChatExtras;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.api.ServerProperties;

public class OtherCMD implements Listener {
	
	@EventHandler
	public void on(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String cmd = e.getMessage().toLowerCase();
        cmd = cmd.replaceAll(cmd, cmd.toLowerCase());
        
        ServerProperties.setNewCommand(p, cmd.replaceAll("/", ""));
        
        if(cmd.startsWith("/help") || cmd.startsWith("/hilfe")) {
        	e.setCancelled(true);
        	p.sendMessage("");
        	p.sendMessage(Main.PREFIX+"§3Help:");
        	p.sendMessage(" §3/Ask §bAsk a question!");
        	p.sendMessage(" §3/Discord §bShows our discord!");
        	p.sendMessage(" §3/Website §bLink to our website!");
        	p.sendMessage(" §3/Apply §bGives info on applications!");
        	p.sendMessage(" §3/Staff §bShows all online staff!");
        	p.sendMessage("");
        	Extras.playSound(p);
        }
        
        else if(cmd.startsWith("/discord")) {
        	e.setCancelled(true);
        	p.sendMessage("");
        	p.sendMessage(Main.PREFIX+"§3Our Discord:");
        	p.sendMessage(" §bdiscord.yzaimoglu.com");
        	p.sendMessage("");
        	Extras.playSound(p);
        }
        
        else if(cmd.startsWith("/website")) {
        	e.setCancelled(true);
        	p.sendMessage("");
        	p.sendMessage(Main.PREFIX+"§3Our Website:");
        	p.sendMessage(" §bdev.yzaimoglu.com");
        	p.sendMessage("");
        	Extras.playSound(p);
        }
        
        else if(cmd.startsWith("/apply")) {
        	e.setCancelled(true);
        	p.sendMessage("");
        	p.sendMessage(Main.PREFIX+"§3Our application process:");
        	p.sendMessage(" §bIn order to apply for a rank on our server, you need to have some specific character traits and knowledges that the specific rank requires.");
        	p.sendMessage(" §bYou can read about all the requirements on our §3/Website§b! Once you think that you are eligible to apply for a rank, you can simply do that under §3apply.yzaimoglu.com§b!");
        	p.sendMessage(" §bWe are currently seeking for §eStaff§b!");
        	p.sendMessage("");
        	Extras.playSound(p);
        }
        
        else if(cmd.startsWith("/staff")) {
        	int i = 0;
        	List<String> staff_online = new ArrayList<>();
        	for(Player all : Bukkit.getOnlinePlayers()) {
        		if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
        			staff_online.add("§6"+all.getName());
        		} else if(PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
        			staff_online.add("§e"+all.getName());
        		}
        	}
        	p.sendMessage(Main.PREFIX+"§3There are currently §b"+staff_online.size()+" §3staff members online:");
        	for(String staff : staff_online) {
        		p.sendMessage(" §3- §b"+staff);
        	}
        	Extras.playSound(p);
        } else {
        	 if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
             	return;
             } else {
             	if(ChatExtras.checkForBlockedCmds(cmd)) {
             		e.setCancelled(true);
             		Extras.noPerm(p);
             		for(Player all : Bukkit.getOnlinePlayers()) {
             			if(PlayerFile.getRank(all).equalsIgnoreCase("Administrator") || PlayerFile.getRank(p).equalsIgnoreCase("Staff")) {
             				all.sendMessage(Main.PREFIX+"§3The player §b"+p.getName()+" §3tried to use a blocked command!");
             				all.sendMessage(" §3Command: §b/"+cmd);
             			} 
             		}
             		return;
             	} else {
             		e.setCancelled(false);
             		return;
             	}
             }
        }
        
	}
	
    @EventHandler(priority = EventPriority.HIGH)
    public void help(PlayerCommandPreprocessEvent e) {
        if(!e.isCancelled()) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null) {
            	e.setCancelled(true);
                p.sendMessage(Main.PREFIX+"§cUnknown Command! §8[§c"+msg+"§8]");
                return;
            } else {
            	e.setCancelled(false);
            	return;
            }
        } else {
        	e.setCancelled(false);
        	return;
        }
    }
	
}
