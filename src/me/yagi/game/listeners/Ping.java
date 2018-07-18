package me.yagi.game.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.yagi.game.api.ServerProperties;

public class Ping implements Listener {

	private int i = 0;
	
	@EventHandler
	public void on(ServerListPingEvent e) {
		e.setMaxPlayers(ServerProperties.getSlots());
		
		String motd_line1 = ServerProperties.getMotdLine1();
		List<String> motd_line2_list = ServerProperties.getMotdLine2();

		if(i == motd_line2_list.size()) {
			i = 0;
		}
		
		String motd_line2 = motd_line2_list.get(i);
		i++;
		e.setMotd(motd_line1+"\n"+motd_line2);
	}
	
}
