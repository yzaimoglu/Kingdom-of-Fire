package me.yagi.game;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.yagi.game.api.ChatExtras;
import me.yagi.game.api.ItemManager;
import me.yagi.game.api.Level;
import me.yagi.game.api.NPCManager;
import me.yagi.game.api.ServerProperties;
import me.yagi.game.commands.CommandsCMD;
import me.yagi.game.commands.ConsoleCMD;
import me.yagi.game.commands.FlyCMD;
import me.yagi.game.commands.FriendCMD;
import me.yagi.game.commands.GamemodeCMD;
import me.yagi.game.commands.InventorySeeCMD;
import me.yagi.game.commands.OtherCMD;
import me.yagi.game.commands.PlayerInfoCMD;
import me.yagi.game.commands.RankCMD;
import me.yagi.game.commands.SlotCMD;
import me.yagi.game.commands.TeleportCMD;
import me.yagi.game.commands.TeleportHereCMD;
import me.yagi.game.commands.TestCMD;
import me.yagi.game.commands.WordsCMD;
import me.yagi.game.commands.admin.AdminCMD;
import me.yagi.game.commands.gamemaster.GamemasterCMD;
import me.yagi.game.commands.level.SetEXPCMD;
import me.yagi.game.commands.level.SetLevelCMD;
import me.yagi.game.commands.level.SetRawEXPCMD;
import me.yagi.game.listeners.Chat;
import me.yagi.game.listeners.Citizens;
import me.yagi.game.listeners.JoinQuit;
import me.yagi.game.listeners.OtherListeners;
import me.yagi.game.listeners.Ping;
import me.yagi.game.listeners.PlayerHorse;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	public static String PREFIX = "§8> §3";
	public static String NOPERM = PREFIX+"§cInsufficient Permissions!";
	
	private static int broadcast = 1;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getServer().getConsoleSender().sendMessage("§aStarting Server...");
		Bukkit.getServer().getConsoleSender().sendMessage("§aInitialising Plugin...");

		try {
			init();			
		} catch (Exception e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage("§cPlugin initialisation failed...");
			return;
		}
		
		Bukkit.getServer().getConsoleSender().sendMessage("§aPlugin successfully initialised!");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("§cStopping Server...");
	}
	
	private void init() {
		
		initListeners();
		initCommands();
		
		createFiles();
		repeatingScheduler();
	}
	
	private void initListeners() {
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinQuit(), this);
		pm.registerEvents(new Chat(), this);
		pm.registerEvents(new AdminCMD(), this);
		pm.registerEvents(new InventorySeeCMD(), this);
		pm.registerEvents(new OtherCMD(), this);
		pm.registerEvents(new Ping(), this);
		pm.registerEvents(new Citizens(), this);
		pm.registerEvents(new PlayerHorse(), this);
		pm.registerEvents(new OtherListeners(), this);
//		pm.registerEvents(new TabListener(), this);
		
	}
	
	private void initCommands() {
		getCommand("admin").setExecutor(new AdminCMD());
		getCommand("rank").setExecutor(new RankCMD());
		getCommand("gamemode").setExecutor(new GamemodeCMD());
		getCommand("test").setExecutor(new TestCMD());
		getCommand("teleport").setExecutor(new TeleportCMD());
		getCommand("teleporthere").setExecutor(new TeleportHereCMD());
		getCommand("playerinfo").setExecutor(new PlayerInfoCMD());
		getCommand("inventorysee").setExecutor(new InventorySeeCMD());
		getCommand("fly").setExecutor(new FlyCMD());
		getCommand("words").setExecutor(new WordsCMD());
		getCommand("commands").setExecutor(new CommandsCMD());
		getCommand("setlevel").setExecutor(new SetLevelCMD());
		getCommand("setexp").setExecutor(new SetEXPCMD());
		getCommand("setrawexp").setExecutor(new SetRawEXPCMD());
		getCommand("console").setExecutor(new ConsoleCMD());
		getCommand("slot").setExecutor(new SlotCMD());
		getCommand("gamemaster").setExecutor(new GamemasterCMD());
		getCommand("friend").setExecutor(new FriendCMD());
	}
	
	private void repeatingScheduler() {
		
		broadcast = 1;
		BossBar broadcast_bar1 = Bukkit.createBossBar("§8[§c§l!§8] §3We now have an integrated Permissions System!", BarColor.RED, BarStyle.SOLID);
		BossBar broadcast_bar2 = Bukkit.createBossBar("§8[§c§l!§8] §3If you have §bany questions§3, feel free to ask them!", BarColor.RED, BarStyle.SOLID);
		BossBar broadcast_bar3 = Bukkit.createBossBar("§8[§c§l!§8] §3You can simply apply by executing §b/apply§3!", BarColor.RED, BarStyle.SOLID);
		BossBar broadcast_bar4 = Bukkit.createBossBar("§8[§c§l!§8] §3Its the §bmost wonderful time §3of the year!", BarColor.RED, BarStyle.SOLID);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {

					if(broadcast == 1) {
						broadcast_bar4.hide();
						broadcast_bar4.removeAll();
						for(Player all : Bukkit.getOnlinePlayers()) {
							broadcast_bar1.addPlayer(all);
						}
						broadcast_bar1.show();
						broadcast++;
					} else if(broadcast == 2) {
						broadcast_bar1.hide();
						broadcast_bar1.removeAll();
						for(Player all : Bukkit.getOnlinePlayers()) {
							broadcast_bar2.addPlayer(all);
						}
						broadcast_bar2.show();
						broadcast++;
					} else if(broadcast == 3) {
						broadcast_bar2.hide();
						broadcast_bar2.removeAll();
						for(Player all : Bukkit.getOnlinePlayers()) {
							broadcast_bar3.addPlayer(all);
						}
						broadcast_bar3.show();
						broadcast++;
					} else if(broadcast == 4) {
						broadcast_bar3.hide();
						broadcast_bar3.removeAll();
						for(Player all : Bukkit.getOnlinePlayers()) {
							broadcast_bar4.addPlayer(all);
						}
						broadcast_bar4.show();
						broadcast = 1;
					} else {
						
					}
				}
				
			
		}.runTaskTimer(Main.getMain(), 0, 10*20);
	}

	private void createFiles() {
		if(!ChatExtras.checkForBlockedWordsFile()) {
			ChatExtras.createBlockedWordsFile();
		}
		if(!ChatExtras.checkForBlockedCommandsFile()) {
			ChatExtras.createBlockedCommandsFile();
		}
		if(!Level.checkLevelFile()) {
			Level.createLevelFile();
		}
		if(!ServerProperties.checkServerFiles()) {
			ServerProperties.createServerFiles();
		}
		
		if(!NPCManager.checkNPCFile()) {
			NPCManager.createNPCFile();
		}
		
		if(!ServerProperties.logExists()) {
			ServerProperties.createLog();
		}
		
		ItemManager.createTestItemFile();
	}
	
	public static Main getMain() {
		return plugin;
	}

}
