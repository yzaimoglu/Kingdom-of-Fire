package me.yagi.game.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.yagi.game.api.Extras;
import me.yagi.game.api.NPCManager;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;

public class Citizens implements Listener {

	private int current = 1;
	
	@EventHandler
	public void on(NPCRightClickEvent e) {
		NPC npc = e.getNPC();
		Player p = (Player) e.getClicker();
		
		if(!NPCManager.isTextAvailable(npc.getId())) {
			return;
		}

		p.sendMessage(" §7["+(current)+"/"+NPCManager.getNPCText(npc.getId()).size()+"] "+NPCManager.getNamePrefix(npc.getId())+NPCManager.removeColorCodes(npc.getName())+"§7: "+ NPCManager.getMessagePrefix(npc.getId())+NPCManager.getNPCText(npc.getId()).get(current-1).replaceAll("%player%", p.getName()));
		Extras.playSound(p);
		current++;
		
		if(current == NPCManager.getNPCText(npc.getId()).size()+1) {
			p.sendMessage("");
			current = 1;
		}
		
	}
}
