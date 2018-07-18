package me.yagi.game.commands.admin;

import fr.minuskube.inv.ClickableItem;
import me.yagi.game.Main;
import me.yagi.game.api.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class AdminManagePlayersMenuProvider implements InventoryProvider {

	public static String name_player;

	@Override
	public void init(Player p, InventoryContents contents) {
		
		if(name_player == null) {
			p.sendMessage(Main.PREFIX+"§cAn error occured, please contact an Administrator!");
			return;
		}

		contents.fillBorders(ClickableItem.empty(InventoryManager.createItemWithMaterial(Material.STAINED_GLASS_PANE, (short)7, 1, "§6", AdminCMD.leer)));
        contents.set(1,2, ClickableItem.empty(InventoryManager.createSkull(name_player, "§6"+name_player)));
		
	}

	@Override
	public void update(Player p, InventoryContents contents) {
		
	}

}
