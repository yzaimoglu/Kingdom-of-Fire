package me.yagi.game.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftOfflinePlayer;
import org.bukkit.entity.Player;

import com.comphenix.protocol.wrappers.WrappedGameProfile;

import me.yagi.game.api.PlayerFile;
import pl.kacperduras.protocoltab.ProtocolTabAPI;
import pl.kacperduras.protocoltab.manager.ProtocolTab;

public class TabListener {
	
	private static WrappedGameProfile profile;
	
	public static void setTablist(Player p) {
		
		profile = new WrappedGameProfile(p.getUniqueId(), ProtocolTab.BLANK_TEXT);
		
		for (int i = 0; i < 80; i++) {
            ProtocolTabAPI.getTablist(p).setSlot(i, ProtocolTab.BLANK_TEXT);
        }
		
		List<String> playersonline = new ArrayList<>(), stafflist = new ArrayList<>();
		for(Player all1 : Bukkit.getOnlinePlayers()) {
			if(PlayerFile.getRank(all1).equalsIgnoreCase("Administrator")) {
				playersonline.add("§6"+all1.getName());
			} else if(PlayerFile.getRank(all1).equalsIgnoreCase("Staff")) {
				playersonline.add("§e"+all1.getName());
			} else if(PlayerFile.getBuilderStatus(all1) || PlayerFile.getGMStatus(all1)) {
				playersonline.add("§3"+all1.getName());
			} else {
				playersonline.add("§b"+all1.getName());
			}
		}
		
		ProtocolTabAPI.getTablist(p).setSlot(0, "  §3§lGlobal §3[§b"+Bukkit.getOnlinePlayers().size()+"§3]");
		
		for (int i = 1; i < 19; i++) {
			try {
				ProtocolTabAPI.getTablist(p).setSlot(i, playersonline.get(i-1));
			} catch (IndexOutOfBoundsException exc) {
				
			}
        }
		
		for(Player all2 : Bukkit.getOnlinePlayers()) {
			if(PlayerFile.getRank(all2).equalsIgnoreCase("Administrator")) {
				stafflist.add("§6"+all2.getName());
			} else if(PlayerFile.getRank(all2).equalsIgnoreCase("Staff")) {
				stafflist.add("§e"+all2.getName());
			} else if(PlayerFile.getBuilderStatus(all2) || PlayerFile.getGMStatus(all2)) {
				stafflist.add("§3"+all2.getName());
			} else {
				
			}
		}
		
		ProtocolTabAPI.getTablist(p).setSlot(20, "  §6§lTeam §6[§e"+stafflist.size()+"§6]");

		for (int i = 21; i < 39; i++) {
			try {
				ProtocolTabAPI.getTablist(p).setSlot(i, stafflist.get(i-21));
			} catch (IndexOutOfBoundsException exc) {
				
			}
        }
		
	}
	
	public static void updateTablist(Player p) {
		ProtocolTabAPI.getTablist(p).update(p);
	}

}
