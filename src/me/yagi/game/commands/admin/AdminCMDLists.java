package me.yagi.game.commands.admin;

import java.util.ArrayList;
import java.util.List;

public class AdminCMDLists {

	public static List<String> gamemasters, builders = new ArrayList<>();
	
	public static void setLists() {
		gamemasters.add("§8§m----------------------");
		gamemasters.add("§6Manage the Gamemasters:");
		gamemasters.add("§eYou can either remove or add");
		gamemasters.add("§ea player from the Gamemasters!");
		
		builders.add("§8§m----------------------");
		builders.add("§6Manage the Builders:");
		builders.add("§eYou can either remove or add");
		builders.add("§ea player from the Builders!");
	}
	
}
