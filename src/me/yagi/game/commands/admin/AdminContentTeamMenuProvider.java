package me.yagi.game.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.yagi.game.Main;
import me.yagi.game.api.InventoryManager;
import me.yagi.game.api.PlayerFile;
import me.yagi.game.api.anvilgui.AnvilGUI;
import me.yagi.game.commands.builder.BuilderInventories;
import me.yagi.game.commands.gamemaster.GamemasterInventories;

public class AdminContentTeamMenuProvider implements InventoryProvider {

	private String playername;
	
	@SuppressWarnings("deprecation")
	@Override
	public void init(Player p, InventoryContents contents) {
		contents.fillBorders(ClickableItem.empty(InventoryManager.createItemWithMaterial(Material.STAINED_GLASS_PANE, (short) 7, 1, "§6", AdminCMD.leer)));
        contents.set(2, 8, ClickableItem.of(InventoryManager.createSkull("MHF_ArrowLeft", "§6Back to Main Menu"), consumer -> {
        	if(consumer.isLeftClick()) {
        		AdminInventories.ContentTeamMenu.close(p);
        		AdminInventories.MainMenu.open(p);
        	}
        }));
		// GAMEMASTER
		contents.set(1, 3, ClickableItem.of(InventoryManager.createItem(307, "§6Manage Gamemasters", AdminCMDLists.gamemasters), e -> {
            if(e.isLeftClick()) {
                contents.fill(ClickableItem.empty(new ItemStack(Material.AIR)));
                contents.fillBorders(ClickableItem.empty(InventoryManager.createItemWithMaterial(Material.STAINED_GLASS_PANE, (short) 7, 1, "§6", AdminCMD.leer)));
                contents.set(1, 2, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.STAINED_CLAY, (short) 5, 1, "§aAdd Gamemaster", AdminCMD.leer), e1 -> {
                	if(e1.isLeftClick()) {
                		new AnvilGUI(Main.getMain(), p, "§6Player Name", new AnvilGUI.ClickHandler() {
							
							@Override
							public String onClick(Player clicker, String input) {
								playername = input;
								if(PlayerFile.getGMStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())) {
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis already a Gamemaster!");
								} else {
									PlayerFile.setGMStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString(), true);
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis now a Gamemaster!");
								}
								return null;
							}
						});
       
                	} else if(e1.isRightClick()) {
                		
                	}
                }));
                
                contents.set(1, 3, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.STAINED_CLAY, (short) 14, 1, "§cRemove Gamemaster", AdminCMD.leer), e2 -> {
                	if(e2.isLeftClick()) {
                		new AnvilGUI(Main.getMain(), p, "§6Player Name", new AnvilGUI.ClickHandler() {
							
							@Override
							public String onClick(Player clicker, String input) {
								playername = input;
								if(!PlayerFile.getGMStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())) {
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis not a Gamemaster!");
								} else {
									PlayerFile.setGMStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString(), false);
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis not a Gamemaster anymore!");
								}
								return null;
							}
						});
                	} else if(e2.isRightClick()) {
                		
                	}
                }));
                
                contents.set(1, 4, ClickableItem.of(InventoryManager.createItem(133, "§6List online Gamemasters"), e3 -> {
                	
                	if(e3.isLeftClick()) {
                		List<String> ongamemasters = new ArrayList<>();
                		for(Player all : Bukkit.getOnlinePlayers()) {
                			if(PlayerFile.getGMStatus(all)) {
                				ongamemasters.add(all.getName());
                			}
                		}
                		p.sendMessage("");
                		p.sendMessage(Main.PREFIX+"§3There are currently §b"+ongamemasters.size()+" §3online Gamemasters:");
                		for(String all : ongamemasters) {
                			p.sendMessage(" §3- §b"+all);
                		}
                		AdminInventories.ContentTeamMenu.close(p);
                	}
                	
                }));
                
                contents.set(1, 5, ClickableItem.of(InventoryManager.createItem(137, "§6List all Gamemasters"), e4 -> {
                	
                	if(e4.isLeftClick()) {
                		List<String> allgamemasters = new ArrayList<>();
                		for(OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                			if(PlayerFile.getGMStatus(all.getUniqueId().toString())) {
                				allgamemasters.add(PlayerFile.getCurrentName(all.getUniqueId().toString()));
                			}
                		}
                		p.sendMessage("");
                		p.sendMessage(Main.PREFIX+"§3There are currently §b"+allgamemasters.size()+" §3Gamemasters:");
                		for(String all : allgamemasters) {
                			p.sendMessage(" §3- §b"+all);
                		}
                		AdminInventories.ContentTeamMenu.close(p);
                	}
                	
                }));
                
                contents.set(1, 6, ClickableItem.of(InventoryManager.createItem(170, "§6Open Gamemaster Menu"), e5 -> {
                	
                	if(e5.isLeftClick()) {
                		AdminInventories.ContentTeamMenu.close(p);
                		GamemasterInventories.MainMenu.open(p);
                	}
                	
                }));
                
                contents.set(2, 8, ClickableItem.of(InventoryManager.createSkull("MHF_ArrowLeft", "§6Back to Content Menu"), e5 -> {
                	
                	if(e5.isLeftClick()) {
                		AdminInventories.ContentTeamMenu.close(p);
                		AdminInventories.ContentTeamMenu.open(p);
                	}
                	
                }));
            }
            else if(e.isRightClick()) {
            	
            }
        }));
		
		
		// BUILDER
		contents.set(1, 5, ClickableItem.of(InventoryManager.createItem(Material.HAY_BLOCK, (short) 0, "§6Manage Builders", AdminCMDLists.builders), e -> {
			
			if(e.isLeftClick())  {
				contents.fill(ClickableItem.empty(new ItemStack(Material.AIR)));
                contents.fillBorders(ClickableItem.empty(InventoryManager.createItemWithMaterial(Material.STAINED_GLASS_PANE, (short) 7, 1, "§6", AdminCMD.leer)));
                contents.set(1,  2, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.STAINED_CLAY, (short) 5, 1, "§aAdd Builder", AdminCMD.leer), e2 -> {
                	
                	if(e2.isLeftClick()) {
                		new AnvilGUI(Main.getMain(), p, "§6Player Name", new AnvilGUI.ClickHandler() {
							
							@Override
							public String onClick(Player clicker, String input) {
								playername = input;
								if(PlayerFile.getBuilderStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())) {
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis already a Builder!");
								} else {
									PlayerFile.setBuilderStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString(), true);
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis now a Builder!");
								}
								return null;
							}
						});
                	}
                	
                }));
                contents.set(1,  3, ClickableItem.of(InventoryManager.createItemWithMaterial(Material.STAINED_CLAY, (short) 14, 1, "§cRemove Builder", AdminCMD.leer), e2 -> {
                	
                	if(e2.isLeftClick()) {
                		new AnvilGUI(Main.getMain(), p, "§6Player Name", new AnvilGUI.ClickHandler() {
							
							@Override
							public String onClick(Player clicker, String input) {
								playername = input;
								if(!PlayerFile.getBuilderStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())) {
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis not a Builder!");
								} else {
									PlayerFile.setBuilderStatus(Bukkit.getOfflinePlayer(playername).getUniqueId().toString(), false);
									clicker.sendMessage(Main.PREFIX+"§3"+playername+" §bis not a Builder anymore!");
								}
								return null;
							}
						});
                	}
                	
                }));
                
                contents.set(1, 4, ClickableItem.of(InventoryManager.createItem(133, "§6List online Builders"), e3 -> {
                	
                	if(e3.isLeftClick()) {
                		List<String> onbuilders = new ArrayList<>();
                		for(Player all : Bukkit.getOnlinePlayers()) {
                			if(PlayerFile.getBuilderStatus(all)) {
                				onbuilders.add(all.getName());
                			}
                		}
                		p.sendMessage("");
                		p.sendMessage(Main.PREFIX+"§3There are currently §b"+onbuilders.size()+" §3online Builders:");
                		for(String all : onbuilders) {
                			p.sendMessage(" §3- §b"+all);
                		}
                		AdminInventories.ContentTeamMenu.close(p);
                	}
                	
                }));
                
                contents.set(1, 5, ClickableItem.of(InventoryManager.createItem(137, "§6List all Builders"), e4 -> {
                	
                	if(e4.isLeftClick()) {
                		List<String> allbuilders = new ArrayList<>();
                		for(OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                			if(PlayerFile.getBuilderStatus(all.getUniqueId().toString())) {
                				allbuilders.add(PlayerFile.getCurrentName(all.getUniqueId().toString()));
                			}
                		}
                		p.sendMessage("");
                		p.sendMessage(Main.PREFIX+"§3There are currently §b"+allbuilders.size()+" §3Builders:");
                		for(String all : allbuilders) {
                			p.sendMessage(" §3- §b"+all);
                		}
                		AdminInventories.ContentTeamMenu.close(p);
                	}
                	
                }));
                
                contents.set(1, 6, ClickableItem.of(InventoryManager.createItem(170, "§6Open Builder Menu"), e5 -> {
                	
                	if(e5.isLeftClick()) {
                		AdminInventories.ContentTeamMenu.close(p);
                		BuilderInventories.MainMenu.open(p);
                	}
                	
                }));
                
               contents.set(2, 8, ClickableItem.of(InventoryManager.createSkull("MHF_ArrowLeft", "§6Back to Content Menu"), e6 -> {
                	
                	if(e6.isLeftClick()) {
                		AdminInventories.ContentTeamMenu.close(p);
                		AdminInventories.ContentTeamMenu.open(p);
                	}
                	
                }));
			}
			
		}));
		
		
	}

	@Override
	public void update(Player p, InventoryContents contents) {
		
		
	}

}
