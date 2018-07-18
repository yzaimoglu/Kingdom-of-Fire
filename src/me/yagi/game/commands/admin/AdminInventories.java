package me.yagi.game.commands.admin;

import fr.minuskube.inv.SmartInventory;

public class AdminInventories {

	public static final SmartInventory MainMenu = SmartInventory.builder()
			.id("adminmainmenu")
			.provider(new AdminMainMenuProvider())
			.size(3,9)
			.title("§6Administrator §8- §6Main Menu")
			.closeable(true)
			.build();
	
	public static final SmartInventory ContentTeamMenu = SmartInventory.builder()
			.id("contentteammenu")
			.provider(new AdminContentTeamMenuProvider())
			.size(3,9)
			.title("§6Administrator §8- §6Content Team")
			.closeable(true)
			.build();
	
	public static final SmartInventory ManageItemsMenu = SmartInventory.builder()
			.id("releaseditemsmenu")
			.provider(new AdminManageItemsMenuProvider())
			.size(3,9)
			.title("§6Administrator §8- §6Released Items")
			.closeable(true)
			.build();
	
	public static final SmartInventory ManagePlayersMenu = SmartInventory.builder()
			.id("manageplayersmenu")
			.provider(new AdminManagePlayersMenuProvider())
			.size(3,9)
			.title("§6Administrator §8- §6Manage Players")
			.closeable(true)
			.build();
	
}
