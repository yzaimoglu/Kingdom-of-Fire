package me.yagi.game.listeners;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class OtherListeners implements Listener {

	@EventHandler
	public void on(PlayerBedEnterEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void on(EntitySpawnEvent e) {
	    if (e.getEntity() instanceof ExperienceOrb) { e.setCancelled(true); }
	}
	
	@EventHandler
	public void on(FoodLevelChangeEvent e) {
		e.setFoodLevel(20);
		e.setCancelled(true);
	}
	
	
}
