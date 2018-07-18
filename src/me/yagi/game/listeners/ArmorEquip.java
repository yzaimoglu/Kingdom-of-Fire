package me.yagi.game.listeners;

import me.yagi.game.api.PlayerFile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmorEquip {

    public static void checkForArmor(Player p) {

        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
            try {
                String health_lore = helmet.getItemMeta().getLore().get(3);
                health_lore = health_lore.replaceAll("§cHealth: ", "")
                        .replaceAll("§8Health: ", "")
                        .replaceAll("§7", "")
                        .replaceAll("§c", "")
                        .replaceAll("+", "");

                int helmet_health = Integer.valueOf(health_lore);

                health_lore = chestplate.getItemMeta().getLore().get(3);
                health_lore = health_lore.replaceAll("§cHealth: ", "")
                        .replaceAll("§8Health: ", "")
                        .replaceAll("§7", "")
                        .replaceAll("§c", "")
                        .replaceAll("+", "");
                int chestplate_health = Integer.valueOf(health_lore);

                health_lore = leggings.getItemMeta().getLore().get(3);
                health_lore = health_lore.replaceAll("§cHealth: ", "")
                        .replaceAll("§8Health: ", "")
                        .replaceAll("§7", "")
                        .replaceAll("§c", "")
                        .replaceAll("+", "");
                int leggings_health = Integer.valueOf(health_lore);

                health_lore = boots.getItemMeta().getLore().get(3);
                health_lore = health_lore.replaceAll("§cHealth: ", "")
                        .replaceAll("§8Health: ", "")
                        .replaceAll("§7", "")
                        .replaceAll("§c", "")
                        .replaceAll("+", "");
                int boots_health = Integer.valueOf(health_lore);

                int maxhealth = helmet_health+chestplate_health+leggings_health+boots_health;
                PlayerFile.setMaxHealth(p, maxhealth);
            } catch (Exception e) {
                p.sendMessage(me.yagi.game.Main.PREFIX+"§cAn error occured, please contact an Administrator!");
            }

    }

}
