package me.yagi.game.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.yagi.game.Main;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.api.anvilgui.AnvilGUI;

public class AdminMainMenuProvider implements InventoryProvider {
	
	public static String playername = null;
	
	@SuppressWarnings("deprecation")
	@Override
	public void init(Player p, InventoryContents contents) {
		contents.fillBorders(ClickableItem.empty(InventoryManager.createItemWithMaterial(Material.STAINED_GLASS_PANE, (short) 7, 1, "§6", AdminCMD.leer)));
		
		contents.set(2, 8, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.BARRIER, (short)0, 1, "§cClose Menu", AdminCMD.leer), e -> {
			
			if(e.isLeftClick()) {
				AdminInventories.MainMenu.close(p);
			}
			
		}));
		
		contents.set(1, 2, ClickableItem.of(InventoryManager.createItem(25, "§6Content Team"), event1 -> {
            if(event1.isLeftClick()) {
               AdminInventories.MainMenu.close(p);
               AdminInventories.ContentTeamMenu.open(p);
            } else if(event1.isRightClick()) {
            	
            }
        }));
		
		contents.set(1, 3, ClickableItem.of(InventoryManager.createItem(267, "§6Manage released Items"), e -> {
			
			if(e.isLeftClick()) {
				AdminInventories.MainMenu.close(p);
				AdminInventories.ManageItemsMenu.open(p);
			}
			
		}));
		
		
		contents.set(1, 5, ClickableItem.of(InventoryManager.createItem(388, "§6Manage Players"), e -> {
			
			if(e.isLeftClick()) {
				new AnvilGUI(Main.getMain(), p, "§6Player name", new AnvilGUI.ClickHandler() {
					
					@Override
					public String onClick(Player clicker, String input) {
						if(PlayerFile.getCurrentName(Bukkit.getOfflinePlayer(input).getUniqueId().toString()) != null) {
							AdminMainMenuProvider.playername = input;
						} else {
							p.sendMessage(Main.PREFIX+"§3"+input+" §bhas never played on this server before!");
							return null;
						}
						AdminManagePlayersMenuProvider.name_player = playername;
						AdminInventories.ManagePlayersMenu.open(p);
						return null;
					}
				});
			}
			
		}));
		
		contents.set(1, 6, ClickableItem.of(InventoryManager.createItem(331, "§6Edit Server Properties"), e -> {
			
			if(e.isLeftClick()) {
				
			}
			
		}));
		
	}

	@Override
	public void update(Player p, InventoryContents contents) {
		
		
	}

}
