package me.yagi.game.api;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.yagi.game.Main;

public class Extras {
	
	public static void playSound(Player p, Sound type) {

		p.playSound(p.getLocation(), type, 1.0F, 1.0F);
	}
	
	public static void playSound(Player p) {

			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
	}
	
	public static void noPerm(Player p) {
		p.sendMessage(Main.NOPERM);
		p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 1.0F);
	}
	


}
