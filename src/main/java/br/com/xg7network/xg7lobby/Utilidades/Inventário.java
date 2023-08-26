package br.com.xg7network.xg7lobby.Utilidades;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
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
        for (String Sinv : cm.getSeletor().getConfigurationSection("inventories").getKeys(false)) {
            if (cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items") != null) {
                for (String itens : cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items").getKeys(false)) {
                    if (e.getCurrentItem() != null) {
                        if (!itens.equals("default")) {
                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items." + itens + ".item")), cm.getSeletor().getInt("Inventarios." + Sinv + ".Itens." + itens + ".amount"));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(cm.getSeletor().getString("inventories." + Sinv + ".items." + itens + ".name").replace("&", "§"));
                            List<String> lore = new ArrayList<>();
                            for (String l : cm.getSeletor().getStringList("inventories." + Sinv + ".items." + itens + ".lore")) {
                                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                    l = PlaceholderAPI.setPlaceholders(p, l);
                                }
                                l = l.replace("&", "§");
                                lore.add(l);
                            }
                            meta.setLore(lore);
                            if (cm.getSeletor().getBoolean("inventories." + Sinv + ".items." + itens + ".glow")) {
                                meta.addEnchant(Enchantment.DURABILITY, 0, true);
                            }

                            item.setItemMeta(meta);
                            if (e.getCurrentItem().isSimilar(item)) {
                                List<String> ações = cm.getSeletor().getStringList("inventories." + Sinv + ".items." + itens + ".actions");
                                ac.executar(ações, p);
                                e.setCancelled(true);
                                return;
                            }
                        } else {
                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items.default.item")));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(" ");
                            item.setItemMeta(meta);
                            if (e.getCurrentItem().isSimilar(item)) {
                                e.setCancelled(true);
                                return;
                            }
                        }
                    }
                }
            }

        }

        if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName()))  {
            if (p.hasPermission(PermissionType.ADMIN.getPerm())) {
                e.setCancelled(!p.hasPermission(PermissionType.ADMIN.getPerm()));
            }
        }

    }

}
