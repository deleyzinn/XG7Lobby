package br.com.xg7network.xg7lobby.Utilidades;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

import static br.com.xg7network.xg7lobby.Comandos.Lobby.Fly.voar;

public class Ações {

    private XG7Lobby pl;

    public Ações (XG7Lobby pl) {
        this.pl = pl;
    }

    public void executar(List<String> ação, Player p) {


        for (String s : ação) {
            if (s.startsWith("[MESSAGE] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {

                        s = s.replace(s2[1], "");
                        s = s.replace("[MESSAGE] ", "");
                        s = s.replace(" [PERMISSION] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[MESSAGE] ", "");
                        s = s.replace(" [!PERMISSION] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else {
                    s = s.replace("[MESSAGE] ", "");
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        s = PlaceholderAPI.setPlaceholders(p, s);
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                }
            } else if (s.startsWith("[COMMAND] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[COMMAND] ", "");
                        s = s.replace(" [PERMISSION] ", "");
                        p.performCommand(s);
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[MENSAGEM] ", "");
                        s = s.replace(" [!PERMISSION] ", "");
                        p.performCommand(s);
                    }
                } else {
                    s = s.replace("[COMMAND] ", "");
                    p.performCommand(s);
                }
            } else if (s.startsWith("[CONSOLE] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[CONSOLE] ", "");
                        s = s.replace(" [PERMISSION] ", "");
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[CONSOLE] ", "");
                        s = s.replace(" [!PERMISSION] ", "");
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
                    }
                } else {
                    s = s.replace("[CONSOLE] ", "");
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
                }
            } else if (s.startsWith("[TITLE] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TITLE] ", "");
                        s = s.replace(" [PERMISSION] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TITLE] ", "");
                        s = s.replace(" [!PERMISSION] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
                    }
                } else {
                    s = s.replace("[TITLE] ", "");
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        s = PlaceholderAPI.setPlaceholders(p, s);
                    }
                    p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
                }
            } else if (s.startsWith("[TIT&SUBTIT] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TIT&SUBTIT] ", "");
                        s = s.replace(" [PERMISSION] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        String[] ac = s.split(" // ");
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TIT&SUBTIT] ", "");
                        s = s.replace(" [!PERMISSION] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        String[] ac = s.split(" // ");
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
                    }
                } else {
                    s = s.replace("[TIT&SUBTIT] ", "");
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        s = PlaceholderAPI.setPlaceholders(p, s);
                    }
                    String[] ac = s.split(" // ");
                    p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
                }
            } else if (s.startsWith("[BROADCAST] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [PERMISSION] ", "");
                        s = s.replace("[BROADCAST] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [!PERMISSION] ", "");
                        s = s.replace("[BROADCAST] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else {
                    s = s.replace("[BROADCAST] ", "");
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        s = PlaceholderAPI.setPlaceholders(p, s);
                    }
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                }
            } else if (s.startsWith("[SUMMON] ")) {
                s = s.replace("[SUMMON] ", "");
                p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(s));

            } else if (s.startsWith("[SOUND] ")) {

                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [PERMISSION] ", "");
                        s = s.replace("[SOUND] ", "");
                        String[] ac = s.split(", ");
                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [!PERMISSION] ", "");
                        s = s.replace("[SOUND] ", "");
                        String[] ac = s.split(", ");
                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));
                    }
                } else {

                    s = s.replace("[SOUND] ", "");
                    String[] ac = s.split(", ");
                    p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));

                }
            } else if (s.startsWith("[OPEN] ")) {

                p.closeInventory();
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[OPEN] ", "");
                        s = s.replace(" [PERMISSION] ", "");
                        if (cm.getSeletor().getConfigurationSection("inventories") != null) {
                            for (String Sinv : cm.getSeletor().getConfigurationSection("inventories").getKeys(false)) {
                                if (Integer.parseInt(s) == cm.getSeletor().getInt("inventories." + Sinv + ".id")) {
                                    Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("inventories." + Sinv + ".lines") * 9, cm.getSeletor().getString("inventories." + Sinv + ".name").replace("&", "§"));

                                    if (cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items") != null) {
                                        for (String itens : cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items").getKeys(false)) {
                                            if (itens.equals("default")) {
                                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items.default.item")));
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(" ");
                                                item.setItemMeta(meta);
                                                for (int i = 0; i < inv.getSize(); i++) {
                                                    inv.setItem(i, item);
                                                }
                                            } else {
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
                                            }
                                        }
                                    }
                                    Bukkit.getScheduler().runTaskLater(pl, () -> p.openInventory(inv), 5);

                                }

                            }
                        }
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[OPEN] ", "");
                        s = s.replace(" [!PERMISSION] ", "");
                        if (cm.getSeletor().getConfigurationSection("inventories") != null) {
                            for (String Sinv : cm.getSeletor().getConfigurationSection("inventories").getKeys(false)) {
                                if (Integer.parseInt(s) == cm.getSeletor().getInt("inventories." + Sinv + ".id")) {
                                    Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("inventories." + Sinv + ".lines") * 9, cm.getSeletor().getString("inventories." + Sinv + ".name").replace("&", "§"));

                                    if (cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items") != null) {
                                        for (String itens : cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items").getKeys(false)) {
                                            if (itens.equals("default")) {
                                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items.default.item")));
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(" ");
                                                item.setItemMeta(meta);
                                                for (int i = 0; i < inv.getSize(); i++) {
                                                    inv.setItem(i, item);
                                                }
                                            } else {
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
                                            }
                                        }
                                    }
                                    Bukkit.getScheduler().runTaskLater(pl, () -> p.openInventory(inv), 5);

                                }

                            }
                        }
                    }
                } else {
                    s = s.replace("[OPEN] ", "");
                    if (cm.getSeletor().getConfigurationSection("inventories") != null) {
                        for (String Sinv : cm.getSeletor().getConfigurationSection("inventories").getKeys(false)) {
                            if (Integer.parseInt(s) == cm.getSeletor().getInt("inventories." + Sinv + ".id")) {
                                Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("inventories." + Sinv + ".lines") * 9, cm.getSeletor().getString("inventories." + Sinv + ".name").replace("&", "§"));

                                if (cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items") != null) {
                                    for (String itens : cm.getSeletor().getConfigurationSection("inventories." + Sinv + ".items").getKeys(false)) {
                                        if (itens.equals("default")) {
                                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("inventories." + Sinv + ".items.default.item")));
                                            ItemMeta meta = item.getItemMeta();
                                            meta.setDisplayName(" ");
                                            item.setItemMeta(meta);
                                            for (int i = 0; i < inv.getSize(); i++) {
                                                inv.setItem(i, item);
                                            }
                                        } else {
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
                                        }
                                    }
                                }
                                Bukkit.getScheduler().runTaskLater(pl, () -> p.openInventory(inv), 5);

                            }

                        }
                    }
                }
            } else if (s.startsWith("[CLOSE]")) {
                p.closeInventory();
            } else if (s.startsWith("[FLY]")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[0])) {
                        if (!voar.containsKey(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                        }
                        if (voar.get(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                            p.setFlying(false);
                            va.mandarMensagem(cm.getMessage().getString("comandos.fly-disabled"), p);
                        } else {
                            voar.put(p.getUniqueId(), true);
                            p.setFlying(true);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-enabled"), p);
                        }
                    }
                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (p.hasPermission(s2[0])) {
                        if (!voar.containsKey(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                        }
                        if (voar.get(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                            p.setFlying(false);
                            va.mandarMensagem(cm.getMessage().getString("comandos.fly-disabled"), p);
                        } else {
                            voar.put(p.getUniqueId(), true);
                            p.setFlying(true);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-enabled"), p);
                        }
                        }
                } else {
                    if (!voar.containsKey(p.getUniqueId())) {
                        voar.put(p.getUniqueId(), false);
                    }
                        if (voar.get(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                            p.setFlying(false);
                            va.mandarMensagem(cm.getMessage().getString("comands.fly-disabled"), p);
                        } else {
                            voar.put(p.getUniqueId(), true);
                            p.setFlying(true);
                            va.mandarMensagem(cm.getMessage().getString("commands.fly-enabled"), p);
                        }
                }
            } else if (s.startsWith("[TP] ")) {
                if (s.contains("[PERMISSION] ")) {
                    String[] s2 = s.split("\\[PERMISSION\\] ");
                    if (p.hasPermission(s2[0])) {
                        s = s.replace("[TP] ", "");
                        s = s.replace(" [PERMISSION] ", "");

                        String[] local = s.split(", ");
                        World w = Bukkit.getWorld(local[0]);
                        if (w != null) {
                            if (local.length >= 5) {
                                Location loc = new Location(w, Double.parseDouble(local[1]), Double.parseDouble(local[2]), Double.parseDouble(local[3]), Float.parseFloat(local[4]), Float.parseFloat(local[5]));
                                p.teleport(loc);

                            } else {
                                Location loc = new Location(w, Double.parseDouble(local[1]), Double.parseDouble(local[2]), Double.parseDouble(local[3]));
                                p.teleport(loc);
                            }
                        }
                    }

                } else if (s.contains("[!PERMISSION] ")) {
                    String[] s2 = s.split("\\[!PERMISSION\\] ");
                    if (!p.hasPermission(s2[0])) {
                        s = s.replace("[TP] ", "");
                        s = s.replace("[!PERMISSION] ", "");

                        String[] local = s.split(", ");
                        World w = Bukkit.getWorld(local[0]);
                        if (w != null) {
                            if (local.length >= 5) {
                                Location loc = new Location(w, Double.parseDouble(local[1]), Double.parseDouble(local[2]), Double.parseDouble(local[3]), Float.parseFloat(local[4]), Float.parseFloat(local[5]));
                                p.teleport(loc);

                            } else {
                                Location loc = new Location(w, Double.parseDouble(local[1]), Double.parseDouble(local[2]), Double.parseDouble(local[3]));
                                p.teleport(loc);
                            }
                        }

                    }
                } else {
                    s = s.replace("[TP] ", "");
                    String[] local = s.split(", ");
                    World w = Bukkit.getWorld(local[0]);
                    if (w != null) {
                        if (local.length >= 5) {
                            Location loc = new Location(w, Double.parseDouble(local[1]), Double.parseDouble(local[2]), Double.parseDouble(local[3]), Float.parseFloat(local[4]), Float.parseFloat(local[5]));
                            p.teleport(loc);

                        } else {
                            Location loc = new Location(w, Double.parseDouble(local[1]), Double.parseDouble(local[2]), Double.parseDouble(local[3]));
                            p.teleport(loc);
                        }
                    }
                }
            }
        }
    }

}

