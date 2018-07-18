package me.yagi.game.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.yagi.game.Main;
import me.yagi.game.api.ChatExtras;
import me.yagi.game.api.Extras;
import me.yagi.game.api.PlayerFile;

public class WordsCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			if(PlayerFile.getRank(p).equalsIgnoreCase("Administrator")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("list")) {
						p.sendMessage(Main.PREFIX+"§bBlocked Words: §3["+ChatExtras.getBlockedWords().size()+"] §7§oPage 1");
						int i = 0;
						int i2 = 1;
						for(String all : ChatExtras.getBlockedWords()) {
							i++;
							p.sendMessage(" §3- §b"+all);
							if(i == 25) {
								i2++;
								p.sendMessage(Main.PREFIX+"§bBlocked Words: §3["+ChatExtras.getBlockedWords().size()+"] §7§oPage "+i2);
								i = 0;
							}
						}
						Extras.playSound(p);
					} else {
						p.sendMessage(Main.PREFIX+"§bUsage: §3/Words <list/add/remove>");
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("add")) {
						String word_to_be_added = args[1];
						List<String> words_list = ChatExtras.getBlockedWords();
						for(String all : words_list) {
							if(all.equalsIgnoreCase(word_to_be_added)) {
								p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_added+" §bis already §3blocked§b...");
								return false;
							}
						}
						
						words_list.add(word_to_be_added);
						ChatExtras.setBlockedWordsFile(words_list);
						p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_added+" §bhas been succesfully §3blocked§b!");
						Extras.playSound(p);
					} else if(args[0].equalsIgnoreCase("remove")) {
						String word_to_be_removed = args[1];
						List<String> words_list = ChatExtras.getBlockedWords();
						for(String all : words_list) {
							if(all.equalsIgnoreCase(word_to_be_removed)) {

							} else {
								p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_removed+" §bis §3not blocked§b...");
								return false;
							}
						}
						
						words_list.remove(word_to_be_removed);
						ChatExtras.setBlockedWordsFile(words_list);
						p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_removed+" §bhas been succesfully §3unblocked§b!");
						Extras.playSound(p);
					} else {
						p.sendMessage(Main.PREFIX+"§bUsage: §3/Words <list/add/remove>");
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Words <list/add/remove>");
				}
			} else {
				Extras.noPerm(p);
			}
			
			
		} else {
			ConsoleCommandSender p = Bukkit.getConsoleSender();
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("list")) {
					p.sendMessage(Main.PREFIX+"§bBlocked Words: §3["+ChatExtras.getBlockedWords().size()+"] §7§oPage 1");
					int i = 0;
					int i2 = 1;
					for(String all : ChatExtras.getBlockedWords()) {
						i++;
						p.sendMessage(" §3- §b"+all);
						if(i == 25) {
							i2++;
							p.sendMessage(Main.PREFIX+"§bBlocked Words: §3["+ChatExtras.getBlockedWords().size()+"] §7§oPage "+i2);
							i = 0;
						}
					}
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Words <list/add/remove>");
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("add")) {
					String word_to_be_added = args[1];
					List<String> words_list = ChatExtras.getBlockedWords();
					for(String all : words_list) {
						if(all.equalsIgnoreCase(word_to_be_added)) {
							p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_added+" §bis already §3blocked§b...");
							return false;
						}
					}
					
					words_list.add(word_to_be_added);
					ChatExtras.setBlockedWordsFile(words_list);
					p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_added+" §bhas been succesfully §3blocked§b!");
				} else if(args[0].equalsIgnoreCase("remove")) {
					String word_to_be_removed = args[1];
					List<String> words_list = ChatExtras.getBlockedWords();
					for(String all : words_list) {
						if(all.equalsIgnoreCase(word_to_be_removed)) {

						} else {
							p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_removed+" §bis §3not blocked§b...");
							return false;
						}
					}
					
					words_list.remove(word_to_be_removed);
					ChatExtras.setBlockedWordsFile(words_list);
					p.sendMessage(Main.PREFIX+"§bThe word §3"+word_to_be_removed+" §bhas been succesfully §3unblocked§b!");
				} else {
					p.sendMessage(Main.PREFIX+"§bUsage: §3/Words <list/add/remove>");
				}
			} else {
				p.sendMessage(Main.PREFIX+"§bUsage: §3/Words <list/add/remove>");
			}
		}
		
		return false;
	}
	
}
