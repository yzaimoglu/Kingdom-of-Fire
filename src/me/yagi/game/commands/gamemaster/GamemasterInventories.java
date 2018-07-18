package me.yagi.game.commands.gamemaster;

import fr.minuskube.inv.SmartInventory;
import me.yagi.game.commands.admin.AdminMainMenuProvider;

public class GamemasterInventories {

	public static final SmartInventory MainMenu = SmartInventory.builder()
			.id("gmmainmenu")
			.provider(new AdminMainMenuProvider())
			.size(3,9)
			.title("§6Gamemaster §8- §6Main Menu")
			.closeable(true)
			.build();
	
}
