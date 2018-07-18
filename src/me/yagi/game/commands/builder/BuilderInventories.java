package me.yagi.game.commands.builder;

import fr.minuskube.inv.SmartInventory;
import me.yagi.game.commands.admin.AdminMainMenuProvider;

public class BuilderInventories {

	public static final SmartInventory MainMenu = SmartInventory.builder()
			.id("buildermainmenu")
			.provider(new AdminMainMenuProvider())
			.size(3,9)
			.title("§6Builder §8- §6Main Menu")
			.closeable(true)
			.build();
	
}
