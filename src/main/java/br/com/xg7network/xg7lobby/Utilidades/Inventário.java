package br.com.xg7network.xg7lobby.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static br.com.xg7network.xg7lobby.XG7Lobby.ac;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Inventário implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            e.setCancelled(true);
            for (String Sinv : cm.getSeletor().getConfigurationSection("Inventários").getKeys(false)) {
                for (String itens : cm.getSeletor().getConfigurationSection("Inventários." + Sinv + ".Itens").getKeys(false)) {
                    if (!itens.equals("default")) {
                        ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + itens + ".item")));
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + itens + ".nome").replace("&", "§"));
                        List<String> lore = new ArrayList<>();
                        for (String l : cm.getSeletor().getStringList("Inventários." + Sinv + ".Itens." + itens + ".lore")) {
                            l = l.replace("&", "§");
                            lore.add(l);
                        }
                        meta.setLore(lore);
                        if (cm.getSeletor().getBoolean("Inventários." + Sinv + ".Itens." + itens + ".glow")) {
                            meta.addEnchant(Enchantment.DURABILITY, 0, true);
                        }

                        item.setItemMeta(meta);

                        if (e.getCurrentItem().isSimilar(item)) {
                            List<String> ações =  cm.getSeletor().getStringList("Inventários." + Sinv + ".Itens." + itens + ".ações");
                            ac.executar(ações, p);
                        }
                    }
                }


            }
        }
    }

}
