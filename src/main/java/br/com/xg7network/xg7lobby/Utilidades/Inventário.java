package br.com.xg7network.xg7lobby.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static br.com.xg7network.xg7lobby.XG7Lobby.ac;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Inventário implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
            for (String Sinv : cm.getSeletor().getConfigurationSection("Inventários").getKeys(false)) {
                for (String invi : cm.getSeletor().getConfigurationSection("Inventários." + Sinv + ".Itens").getKeys(false)) {
                    ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + invi + ".item")));
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + invi + ".nome")));
                    List<String> lore = cm.getSeletor().getStringList("Inventários." + Sinv + ".Itens." + invi + ".lore");
                    for (String l : lore) {
                        l = ChatColor.translateAlternateColorCodes('&', l);
                    }
                    meta.setLore(lore);
                    item.setItemMeta(meta);

                    if (e.getCurrentItem().isSimilar(item)) {

                        List<String> ações = cm.getSeletor().getStringList("Inventários." + Sinv + ".Itens." + invi + ".ações");
                        ac.executar(ações, p);
                    }
                }


            }
            e.setCancelled(true);
        }
    }

}
