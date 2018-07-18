package me.yagi.game.commands.admin;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.yagi.game.Main;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.ItemManager;
import me.yagi.game.api.ServerProperties;
import me.yagi.game.api.anvilgui.AnvilGUI;

public class AdminManageItemsMenuProvider implements InventoryProvider {

	@SuppressWarnings("deprecation")
	@Override
	public void init(Player p, InventoryContents contents) {
		contents.fillBorders(ClickableItem.empty(InventoryManager.createItemWithMaterial(Material.STAINED_GLASS_PANE, (short)7, 1, "§6", AdminCMD.leer)));
        contents.set(2, 8, ClickableItem.of(InventoryManager.createSkull("MHF_ArrowLeft", "§6Back to Main Menu"), consumer -> {
        	if(consumer.isLeftClick()) {
        		AdminInventories.ManageItemsMenu.close(p);
        		AdminInventories.MainMenu.open(p);
        	}
        }));
		contents.set(1, 2, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.STAINED_CLAY, (short)5, 1, "§aAdd released Item", AdminCMD.leer), e -> {
			
			if(e.isLeftClick()) {
				new AnvilGUI(Main.getMain(), p, "§6Item Name", new AnvilGUI.ClickHandler() {
					
					@Override
					public String onClick(Player clicker, String input) {
						String itemname = input;
						if(ServerProperties.getItemList().contains(itemname)) {
							p.sendMessage(Main.PREFIX+"§3"+itemname+" §bis already on the released items list!");
						} else {
							List<String> items = ServerProperties.getItemList();
							items.add(itemname);
							ServerProperties.setItemList(items);
							p.sendMessage(Main.PREFIX+"§3"+itemname+" §bhas been added to the released items list!");
						}
						return null;
					}
				});
			}
			
		}));
		
		contents.set(1, 3, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.STAINED_CLAY, (short)14, 1, "§cRemove released Item", AdminCMD.leer), e -> {
			
			if(e.isLeftClick()) {
				new AnvilGUI(Main.getMain(), p, "§6Item Name", new AnvilGUI.ClickHandler() {
					
					@Override
					public String onClick(Player clicker, String input) {
						String itemname = input;
						if(!ServerProperties.getItemList().contains(itemname)) {
							p.sendMessage(Main.PREFIX+"§3"+itemname+" §bis §cnot §bon the released items list!");
						} else {
							List<String> items = ServerProperties.getItemList();
							items.remove(itemname);
							ServerProperties.setItemList(items);
							p.sendMessage(Main.PREFIX+"§3"+itemname+" §bhas been removed from the released items list!");
						}
						return null;
					}
				});	
			}
			
		}));
		
		contents.set(1, 5, ClickableItem.of(InventoryManager.createItem(137, "§6List all released items"), e -> {
			
			if(e.isLeftClick()) {
				List<String> items = ServerProperties.getItemList();
				
				p.sendMessage(Main.PREFIX+"§3There are currently §b"+items.size()+" §3released items:");
				for(String all : items) {
					p.sendMessage(" §3- §b"+all);
				}
				AdminInventories.ManageItemsMenu.close(p);
			}
			
		}));
		
		contents.set(1, 6, ClickableItem.of(InventoryManager.createItem(267, "§6Give yourself an item"), e -> {
			
			if(e.isLeftClick()) {
				new AnvilGUI(Main.getMain(), p, "§6Item Name", new AnvilGUI.ClickHandler() {
					
					@Override
					public String onClick(Player clicker, String input) {
						String itemname = input;
						if(!ServerProperties.getItemList().contains(itemname)) {
							p.sendMessage(Main.PREFIX+"§3"+itemname+" §bis not on the released items list!");
							p.sendMessage(" §bYou can add it to the list in the admin menu.");
						} else {
							ItemStack item = ItemManager.giveItem(itemname);
							p.getInventory().addItem(item);
							p.sendMessage(Main.PREFIX+"§3"+itemname+" §bhas been added to your inventory!");
						}
						return null;
					}
				});	
				
			}
			
		}));
		
	}

	@Override
	public void update(Player arg0, InventoryContents arg1) {
		
		
	}

}
