package br.com.xg7network.xg7lobby.Utilidades;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Ações {

    public void executar(List<String> ação, Player p) {


        for (String s : ação) {
            if (s.startsWith("[MENSAGEM] ")) {
                s = s.replace("[MENSAGEM] ", "");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
            } else if (s.startsWith("[COMANDO] ")) {
                s = s.replace("[COMANDO] ", "");
                p.performCommand(s);
            } else if (s.startsWith("[CONSOLE] ")) {
                s = s.replace("[CONSOLE] ", "");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
            } else if (s.startsWith("[TITULO] ")) {
                s = s.replace("[TITULO] ", "");
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
            } else if (s.startsWith("[TIT&SUBTIT] ")) {
                s = s.replace("[TIT&SUBTIT] ", "");
                String[] ac = s.split(" // ");
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
            } else if (s.startsWith("[BROADCAST] ")) {
                s = s.replace("[BROADCAST] ", "");
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
            } else if (s.startsWith("[SUMMON] ")) {
                s = s.replace("[SUMMON] ", "");
                p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(s));

            } else if (s.startsWith("[SOM] ")) {

                s = s.replace("[SOM] ", "");
                String[] ac = s.split(", ");
                p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));

            } else if (s.startsWith("[ABRIR] ")) {
                int slot = 0;
                p.closeInventory();
                s = s.replace("[ABRIR] ", "");
                for (String Sinv : cm.getSeletor().getConfigurationSection("Inventários").getKeys(false)) {
                    if (Integer.parseInt(s) == cm.getSeletor().getInt("Inventários." + Sinv + ".id")) {
                        for (String invi : cm.getSeletor().getConfigurationSection("Inventários." + Sinv + ".Itens").getKeys(false)) {
                            Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("Inventários." + Sinv + ".tamanho"), ChatColor.translateAlternateColorCodes('&', cm.getSeletor().getString("Inventários." + Sinv + ".nome")));
                            if (cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + invi + "default") != null && invi.equals("default")) {
                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + invi + "default.item")));
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName("");
                                item.setItemMeta(meta);
                                for (int i = 0; i < inv.getSize() - 1; i++) {
                                    if (i != slot) {
                                        inv.setItem(i, item);
                                    }

                                }

                            } else {
                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + invi + ".item")));
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cm.getSeletor().getString("Inventários." + Sinv + ".Itens." + invi + ".nome")));
                                List<String> lore = cm.getSeletor().getStringList("Inventários." + Sinv + ".Itens." + invi + ".lore");
                                for (String l : lore) {
                                    l = ChatColor.translateAlternateColorCodes('&', l);
                                }
                                meta.setLore(lore);
                                item.setItemMeta(meta);
                                slot = cm.getSeletor().getInt("Inventários." + Sinv + ".Itens." + invi + ".slot");
                                inv.setItem(slot, item);
                            }
                            p.openInventory(inv);
                        }
                    }
                }
            } else if (s.startsWith("[FECHAR] ")) {
                p.closeInventory();
            }
        }

    }

}
