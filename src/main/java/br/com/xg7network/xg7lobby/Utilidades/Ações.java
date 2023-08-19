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
            if (s.startsWith("[MENSAGEM] ")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {

                        s = s.replace(s2[1], "");
                        s = s.replace("[MENSAGEM] ", "");
                        s = s.replace(" [PERMISSÃO] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[MENSAGEM] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else {
                    s = s.replace("[MENSAGEM] ", "");
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        s = PlaceholderAPI.setPlaceholders(p, s);
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                }
            } else if (s.startsWith("[COMANDO] ")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[MENSAGEM] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        p.performCommand(s);
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[MENSAGEM] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        p.performCommand(s);
                    }
                } else {
                    s = s.replace("[COMANDO] ", "");
                    p.performCommand(s);
                }
            } else if (s.startsWith("[CONSOLE] ")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[CONSOLE] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[CONSOLE] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
                    }
                } else {
                    s = s.replace("[CONSOLE] ", "");
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replace("[PLAYER]", p.getName()));
                }
            } else if (s.startsWith("[TITULO] ")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TITULO] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TITULO] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
                    }
                } else {
                    s = s.replace("[TITULO] ", "");
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        s = PlaceholderAPI.setPlaceholders(p, s);
                    }
                    p.sendTitle(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())), null);
                }
            } else if (s.startsWith("[TIT&SUBTIT] ")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TIT&SUBTIT] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        String[] ac = s.split(" // ");
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[TIT&SUBTIT] ", "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        String[] ac = s.split(" // ");
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
                    }
                }
                s = s.replace("[TIT&SUBTIT] ", "");
                String[] ac = s.split(" // ");
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    s = PlaceholderAPI.setPlaceholders(p, s);
                }
                p.sendTitle(ChatColor.translateAlternateColorCodes('&', ac[0].replace("[PLAYER]", p.getName())), ChatColor.translateAlternateColorCodes('&', ac[1].replace("[PLAYER]", p.getName())));
            } else if (s.startsWith("[BROADCAST] ")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        s = s.replace("[BROADCAST] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        s = s.replace("[BROADCAST] ", "");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            s = PlaceholderAPI.setPlaceholders(p, s);
                        }
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
                    }
                }
                s = s.replace("[BROADCAST] ", "");
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    s = PlaceholderAPI.setPlaceholders(p, s);
                }
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s.replace("[PLAYER]", p.getName())));
            } else if (s.startsWith("[SUMMON] ")) {
                s = s.replace("[SUMMON] ", "");
                p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(s));

            } else if (s.startsWith("[SOM] ")) {

                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        s = s.replace("[SOM] ", "");
                        String[] ac = s.split(", ");
                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));
                    }
                } else if (s.contains("[!PERMISSÃO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace(" [!PERMISSÃO] ", "");
                        s = s.replace("[SOM] ", "");
                        String[] ac = s.split(", ");
                        p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));
                    }
                }

                s = s.replace("[SOM] ", "");
                String[] ac = s.split(", ");
                p.getLocation().getWorld().playSound(p.getLocation(), Sound.valueOf(ac[0]), Float.parseFloat(ac[1]), Float.parseFloat(ac[2]));

            } else if (s.startsWith("[ABRIR] ")) {

                p.closeInventory();
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[ABRIR] ", "");
                        s = s.replace(" [PERMISSAO] ", "");
                        if (cm.getSeletor().getConfigurationSection("Inventarios") != null) {
                            for (String Sinv : cm.getSeletor().getConfigurationSection("Inventarios").getKeys(false)) {
                                if (Integer.parseInt(s) == cm.getSeletor().getInt("Inventarios." + Sinv + ".id")) {
                                    Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("Inventarios." + Sinv + ".linhas") * 9, cm.getSeletor().getString("Inventarios." + Sinv + ".nome").replace("&", "§"));

                                    if (cm.getSeletor().getConfigurationSection("Inventarios." + Sinv + ".Itens") != null) {
                                        for (String itens : cm.getSeletor().getConfigurationSection("Inventarios." + Sinv + ".Itens").getKeys(false)) {
                                            if (itens.equals("default")) {
                                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens.default.item")));
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(" ");
                                                item.setItemMeta(meta);
                                                for (int i = 0; i < inv.getSize(); i++) {
                                                    inv.setItem(i, item);
                                                }
                                            } else {
                                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens." + itens + ".item")));
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens." + itens + ".nome").replace("&", "§"));
                                                int slot = cm.getSeletor().getInt("Inventarios." + Sinv + ".Itens." + itens + ".slot") - 1;
                                                List<String> lore = new ArrayList<>();
                                                for (String l : cm.getSeletor().getStringList("Inventarios." + Sinv + ".Itens." + itens + ".lore")) {
                                                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                                        l = PlaceholderAPI.setPlaceholders(p, l);
                                                    }
                                                    l = l.replace("&", "§");
                                                    lore.add(l);
                                                }
                                                meta.setLore(lore);
                                                if (cm.getSeletor().getBoolean("Inventarios." + Sinv + ".Itens." + itens + ".glow")) {
                                                    meta.addEnchant(Enchantment.DURABILITY, 0, true);
                                                }

                                                item.setItemMeta(meta);

                                                inv.setItem(slot, item);
                                            }
                                        }
                                    }
                                    Bukkit.getScheduler().runTaskLater(pl, () -> p.openInventory(inv), 5);

                                }

                            }
                        }
                    }
                } else if (s.contains("[!PERMISSAO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (!p.hasPermission(s2[1])) {
                        s = s.replace(s2[1], "");
                        s = s.replace("[ABRIR] ", "");
                        s = s.replace(" [!PERMISSAO] ", "");
                        if (cm.getSeletor().getConfigurationSection("Inventarios") != null) {
                            for (String Sinv : cm.getSeletor().getConfigurationSection("Inventarios").getKeys(false)) {
                                if (Integer.parseInt(s) == cm.getSeletor().getInt("Inventarios." + Sinv + ".id")) {
                                    Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("Inventarios." + Sinv + ".linhas") * 9, cm.getSeletor().getString("Inventarios." + Sinv + ".nome").replace("&", "§"));

                                    if (cm.getSeletor().getConfigurationSection("Inventarios." + Sinv + ".Itens") != null) {
                                        for (String itens : cm.getSeletor().getConfigurationSection("Inventarios." + Sinv + ".Itens").getKeys(false)) {
                                            if (itens.equals("default")) {
                                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens.default.item")));
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(" ");
                                                item.setItemMeta(meta);
                                                for (int i = 0; i < inv.getSize(); i++) {
                                                    inv.setItem(i, item);
                                                }
                                            } else {
                                                ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens." + itens + ".item")));
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens." + itens + ".nome").replace("&", "§"));
                                                int slot = cm.getSeletor().getInt("Inventarios." + Sinv + ".Itens." + itens + ".slot") - 1;
                                                List<String> lore = new ArrayList<>();
                                                for (String l : cm.getSeletor().getStringList("Inventarios." + Sinv + ".Itens." + itens + ".lore")) {
                                                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                                        l = PlaceholderAPI.setPlaceholders(p, l);
                                                    }
                                                    l = l.replace("&", "§");
                                                    lore.add(l);
                                                }
                                                meta.setLore(lore);
                                                if (cm.getSeletor().getBoolean("Inventarios." + Sinv + ".Itens." + itens + ".glow")) {
                                                    meta.addEnchant(Enchantment.DURABILITY, 0, true);
                                                }

                                                item.setItemMeta(meta);

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
                    s = s.replace("[ABRIR] ", "");
                    if (cm.getSeletor().getConfigurationSection("Inventarios") != null) {
                        for (String Sinv : cm.getSeletor().getConfigurationSection("Inventarios").getKeys(false)) {
                            if (Integer.parseInt(s) == cm.getSeletor().getInt("Inventarios." + Sinv + ".id")) {
                                Inventory inv = Bukkit.createInventory(p, cm.getSeletor().getInt("Inventarios." + Sinv + ".linhas") * 9, cm.getSeletor().getString("Inventarios." + Sinv + ".nome").replace("&", "§"));

                                if (cm.getSeletor().getConfigurationSection("Inventarios." + Sinv + ".Itens") != null) {
                                    for (String itens : cm.getSeletor().getConfigurationSection("Inventarios." + Sinv + ".Itens").getKeys(false)) {
                                        if (itens.equals("default")) {
                                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens.default.item")));
                                            ItemMeta meta = item.getItemMeta();
                                            meta.setDisplayName(" ");
                                            item.setItemMeta(meta);
                                            for (int i = 0; i < inv.getSize(); i++) {
                                                inv.setItem(i, item);
                                            }
                                        } else {
                                            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens." + itens + ".item")));
                                            ItemMeta meta = item.getItemMeta();
                                            meta.setDisplayName(cm.getSeletor().getString("Inventarios." + Sinv + ".Itens." + itens + ".nome").replace("&", "§"));
                                            int slot = cm.getSeletor().getInt("Inventarios." + Sinv + ".Itens." + itens + ".slot") - 1;
                                            List<String> lore = new ArrayList<>();
                                            for (String l : cm.getSeletor().getStringList("Inventarios." + Sinv + ".Itens." + itens + ".lore")) {
                                                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                                    l = PlaceholderAPI.setPlaceholders(p, l);
                                                }
                                                l = l.replace("&", "§");
                                                lore.add(l);
                                            }
                                            meta.setLore(lore);
                                            if (cm.getSeletor().getBoolean("Inventarios." + Sinv + ".Itens." + itens + ".glow")) {
                                                meta.addEnchant(Enchantment.DURABILITY, 0, true);
                                            }

                                            item.setItemMeta(meta);

                                            inv.setItem(slot, item);
                                        }
                                    }
                                }
                                Bukkit.getScheduler().runTaskLater(pl, () -> p.openInventory(inv), 5);

                            }
                        }
                    }
                }
            } else if (s.startsWith("[FECHAR]")) {

                p.closeInventory();
            } else if (s.startsWith("[FLY]")) {
                if (s.contains("[PERMISSAO] ")) {
                    String[] s2 = s.split("\\[PERMISSAO\\] ");
                    if (p.hasPermission(s2[0])) {
                        if (!voar.containsKey(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                        }
                            if (voar.get(p.getUniqueId())) {
                                voar.put(p.getUniqueId(), false);
                                p.setFlying(false);
                                va.mandarMensagem(cm.getMessage().getString("comandos.fly-desativado"), p);
                            } else {
                                voar.put(p.getUniqueId(), true);
                                p.setFlying(true);
                                va.mandarMensagem(cm.getMessage().getString("comandos.fly-ativado"), p);
                            }
                    }
                } else if (s.contains("[!PERMISSAO] ")) {
                    String[] s2 = s.split("\\[!PERMISSAO\\] ");
                    if (p.hasPermission(s2[0])) {
                        if (!voar.containsKey(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                        }
                            if (voar.get(p.getUniqueId())) {
                                voar.put(p.getUniqueId(), false);
                                p.setFlying(false);
                                va.mandarMensagem(cm.getMessage().getString("comandos.fly-desativado"), p);
                            } else {
                                voar.put(p.getUniqueId(), true);
                                p.setFlying(true);
                                va.mandarMensagem(cm.getMessage().getString("comandos.fly-ativado"), p);


                            }
                        }
                } else {
                    if (!voar.containsKey(p.getUniqueId())) {
                        voar.put(p.getUniqueId(), false);
                    }
                        if (voar.get(p.getUniqueId())) {
                            voar.put(p.getUniqueId(), false);
                            p.setFlying(false);
                            va.mandarMensagem(cm.getMessage().getString("comandos.fly-desativado"), p);
                        } else {
                            voar.put(p.getUniqueId(), true);
                            p.setFlying(true);
                            va.mandarMensagem(cm.getMessage().getString("comandos.fly-ativado"), p);
                        }
                }
            }
        }
    }

}

