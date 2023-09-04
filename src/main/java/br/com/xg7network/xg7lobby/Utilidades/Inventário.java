package br.com.xg7network.xg7lobby.Utilidades;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.Modulo.Seletores.HotbarItens;
import br.com.xg7network.xg7lobby.XG7Lobby;
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

    private HotbarItens Hb;
    private XG7Lobby pl;

    public Inventário(XG7Lobby plugin) {
        this.pl = plugin;
        this.Hb = new HotbarItens();
    }

    boolean verifInv(ItemStack item, Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) != null && inv.getItem(i).isSimilar(item)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        for (int i = 0; i < Hb.getItens(p).size() -1; i++) {
            if (e.getCurrentItem().isSimilar(Hb.getItens(p).get(i))) {
                e.setCancelled(!p.hasPermission(PermissionType.INV.getPerm()));
            }
        }
        for (int i = 0; i < Hb.getEJ(p).size() -1; i++) {
            if (e.getCurrentItem().isSimilar(Hb.getEJ(p).get(i))) {
                e.setCancelled(!p.hasPermission(PermissionType.INV.getPerm()));
            }
        }
        for (String Sinv : cm.getSeletor().getConfigurationSection("inventories").getKeys(false)) {
            Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("inventories." + Sinv + ".lines") * 9, cm.getSeletor().getString("inventories." + Sinv + ".name").replace("&", "§"));

            if (cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items") != null) {
                for (String itens : cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items").getKeys(false)) {
                    if (itens.equals("default")) {
                        if (Material.getMaterial(cm.getSeletor().getString("inventories." + Sinv + ".items.default.item")) != null) {
                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items.default.item")));
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(" ");
                            item.setItemMeta(meta);
                            for (int i = 0; i < inv.getSize(); i++) {
                                inv.setItem(i, item);
                            }
                        }
                    } else {
                        if (Material.matchMaterial(cm.getSeletor().getString("inventories." + Sinv + ".items." + itens + ".item")) != null) {

                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items." + itens + ".item")), cm.getSeletor().getInt("inventories." + Sinv + ".items." + itens + ".amount"));
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

                            int slot = cm.getSeletor().getInt("inventories." + Sinv + ".items." + itens + ".slot") - 1;

                            inv.setItem(slot, item);
                            if (verifInv(e.getCurrentItem(), inv)) {
                                e.setCancelled(true);
                                if (e.getCurrentItem().isSimilar(item)) {
                                    List<String> ações = cm.getSeletor().getStringList("inventories." + Sinv + ".items." + itens + ".actions");
                                    ac.executar(ações, p);
                                }
                            }
                        }
                    }
                }
            }

        }

    }



}
