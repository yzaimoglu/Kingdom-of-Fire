package me.yagi.game.listeners;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import me.yagi.game.Main;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.NPCManager;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.listeners.horsespeeds.BlackHorseSpeed;
import me.yagi.game.listeners.horsespeeds.BlackfieldHorseSpeed;
import me.yagi.game.listeners.horsespeeds.BrownHorseSpeed;
import me.yagi.game.listeners.horsespeeds.ChestnutHorseSpeed;
import me.yagi.game.listeners.horsespeeds.GrayHorseSpeed;
import me.yagi.game.listeners.horsespeeds.CreamyHorseSpeed;
import me.yagi.game.listeners.horsespeeds.DarkBrownHorseSpeed;
import me.yagi.game.listeners.horsespeeds.WhiteHorseSpeed;
import me.yagi.game.listeners.horsespeeds.WhitefieldHorseSpeed;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.Controllable;
import net.citizensnpcs.trait.HorseModifiers;

public class PlayerHorse implements Listener {

	@SuppressWarnings({ "deprecation", "static-access" })
	@EventHandler
	public void on(PlayerInteractEvent e) throws IOException {
		Player p = e.getPlayer();
		
		if(!p.getInventory().getItemInHand().getData().getItemType().equals(Material.SADDLE)) {
			return;
		}
		e.setCancelled(true);
		
		int level = PlayerFile.getLevel(p);
		List<String> lore = p.getInventory().getItemInHand().getItemMeta().getLore();
		String requiredlevel = lore.get(2);
		requiredlevel = requiredlevel.replaceAll("§8Requirements: ", "").replaceAll("§7Level ", "").replaceAll("§7", "").replaceAll(" ", "");
		int requiredlevel_int = Integer.valueOf(requiredlevel);
		if(level < requiredlevel_int) {
			p.sendMessage(Main.PREFIX+"§cThis horse requires an §cexperience level §cof §c"+requiredlevel_int+"§c!");
			return;
		}
		
		//WHITE HORSE
		if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6White Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, WhiteHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.WHITE);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.WHITE);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his white horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		} 
		//CREAMY HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Creamy Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, CreamyHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.WHITE_DOTS);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.CREAMY);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his white-dotted horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		} 
		//WHITEFIELD HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Whitefield Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, WhitefieldHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.WHITEFIELD);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.GRAY);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his whitefield horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		}
		//BLACK HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§2Black Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, BlackHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.NONE);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.BLACK);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his black horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		} //BLACKFIELD HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cBlackfield Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, BlackfieldHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.WHITE_DOTS);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.BLACK);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his blackfield horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		}
		//BROWN HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aBrown Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, BrownHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.NONE);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.BROWN);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his brown horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		}
		//DARK-BROWN HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aDark-Brown Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, DarkBrownHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.NONE);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.BROWN);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his dark-brown horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		}
		//GRAY HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cGray Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, GrayHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.NONE);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.GRAY);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his gray horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		}
		//CHESTNUT HORSE
		else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§2Chestnut Horse")) {
			NPC playerhorse = CitizensAPI.getNPCRegistry().createNPC(EntityType.HORSE, UUID.randomUUID(), getRandomNumberInRange(5000, 10000), "§b"+p.getName()+"§7's horse");
			playerhorse.addTrait(Controllable.class);
			playerhorse.getTrait(Controllable.class).setEnabled(true);
			int i = 0;
			while(i != 51) { i++; if(i == 0) { Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§bThis Error warning can be ignored..."); } else { Bukkit.getConsoleSender().sendMessage("");}}i = 0;
			playerhorse.getTrait(Controllable.class).registerControllerType(EntityType.HORSE, ChestnutHorseSpeed.class);
			playerhorse.addTrait(HorseModifiers.class);
			playerhorse.getTrait(HorseModifiers.class).setStyle(Style.NONE);
			playerhorse.getTrait(HorseModifiers.class).setColor(Color.CHESTNUT);
			playerhorse.getTrait(HorseModifiers.class).setSaddle(InventoryManager.createItem(329, "§b"+p.getName()+"§7's horse"));
			
			playerhorse.spawn(p.getLocation());
			playerhorse.getTrait(Controllable.class).mount(p);
			
			NPCManager.setHorse(p, playerhorse.getId(), true);
			while(i != 51) {i++;if(i == 50) {Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§3"+p.getName()+" §bis now riding his chestnut horse!");} else {Bukkit.getConsoleSender().sendMessage("");}}i=0;
		} else {
			return;
		}
		
		
	}
	
	@EventHandler
	public void on(VehicleExitEvent e) {
		Vehicle vehicle = e.getVehicle();
		Player p = (Player) e.getExited();
		if(vehicle.hasMetadata("NPC")) {
			NPC npc = CitizensAPI.getNPCRegistry().getById(NPCManager.getHorseIdByPlayer(p));
			NPCManager.unmountPlayerFromHorse(p, NPCManager.getHorseIdByPlayer(p));
			npc.despawn();
			npc.destroy();
		}
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}
