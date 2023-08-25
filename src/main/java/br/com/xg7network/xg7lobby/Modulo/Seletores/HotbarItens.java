package br.com.xg7network.xg7lobby.Modulo.Seletores;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.xg7network.xg7lobby.XG7Lobby.ac;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class HotbarItens {
    public List<ItemStack> getItens(Player p) {
        List<ItemStack> itens = new ArrayList<>();
        for (String s : cm.getSeletor().getConfigurationSection("Seletores.Seletores").getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Seletores.Seletores." + s + ".item")), cm.getSeletor().getInt("Seletores.Seletores." + s + ".quantidade"));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(cm.getSeletor().getString("Seletores.Seletores." + s + ".nome").replace("&", "§"));
            List<String> lore = new ArrayList<>();
            for (String l : cm.getSeletor().getStringList("Seletores.Seletores." + s + ".lore")) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    l = PlaceholderAPI.setPlaceholders(p, l);
                }
                l = l.replace("&", "§");
                lore.add(l);
            }
            meta.setLore(lore);
            if (cm.getSeletor().getBoolean("Seletores.Seletores." + s + ".glow")) {
                meta.addEnchant(Enchantment.DURABILITY, 0, true);
            }

            item.setItemMeta(meta);
            itens.add(item);
        }
        return itens;
    }

    public List<List<String>> getAcoes() {
        List<List<String>> acoes = new ArrayList<>();
        for (String s : cm.getSeletor().getConfigurationSection("Seletores.Seletores").getKeys(false)) {
            acoes.add(cm.getSeletor().getStringList("Seletores.Seletores." + s + ".acoes"));
        }
        return acoes;
    }

    public List<ItemStack> getEJ(Player p) {
        List<ItemStack> itens = new ArrayList<>();
        ItemStack itemA = new ItemStack(Material.valueOf(cm.getSeletor().getString("EsconderJogadores.Item.item-ativado")));
        ItemStack itemD = new ItemStack(Material.valueOf(cm.getSeletor().getString("EsconderJogadores.Item.item-desativado")));

        ItemMeta metaA = itemA.getItemMeta();
        ItemMeta metaD = itemD.getItemMeta();

        metaA.setDisplayName(cm.getSeletor().getString("EsconderJogadores.Item.nome-ativado").replace("&", "§"));
        List<String> loreA = new ArrayList<>();
        for (String l : cm.getSeletor().getStringList("EsconderJogadores.Item.lore-ativado")) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                l = PlaceholderAPI.setPlaceholders(p, l);
            }
            l = l.replace("&", "§");
            loreA.add(l);
        }
        metaA.setLore(loreA);
        if (cm.getSeletor().getBoolean("EsconderJogadores.Item.glow")) {
            metaA.addEnchant(Enchantment.DURABILITY, 0, true);
        }

        itemA.setItemMeta(metaA);

        metaD.setDisplayName(cm.getSeletor().getString("EsconderJogadores.Item.nome-desativado").replace("&", "§"));
        List<String> loreB = new ArrayList<>();
        for (String l : cm.getSeletor().getStringList("EsconderJogadores.Item.lore-desativado")) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                l = PlaceholderAPI.setPlaceholders(p, l);
            }
            l = l.replace("&", "§");
            loreB.add(l);
        }
        metaD.setLore(loreB);
        if (cm.getSeletor().getBoolean("EsconderJogadores.Item.glow")) {
            metaD.addEnchant(Enchantment.DURABILITY, 0, true);
        }
        itemD.setItemMeta(metaD);

        itemA.setItemMeta(metaA);

        itens.add(itemD);
        itens.add(itemA);
        return itens;
    }

    public void executarAction(Player p, ItemStack item) {

        List<ItemStack> itens = this.getItens(p);
        int i = itens.indexOf(item);
        ac.executar(this.getAcoes().get(i), p);

    }
    int getSlot(ItemStack item, Player p) {
        for (String s : cm.getSeletor().getConfigurationSection("Seletores.Seletores").getKeys(false)) {
            ItemStack Item = new ItemStack(Material.valueOf(cm.getSeletor().getString("Seletores.Seletores." + s + ".item")), cm.getSeletor().getInt("Seletores.Seletores." + s + ".quantidade"));
            ItemMeta meta = Item.getItemMeta();
            meta.setDisplayName(cm.getSeletor().getString("Seletores.Seletores." + s + ".nome").replace("&", "§"));
            List<String> lore = new ArrayList<>();
            for (String l : cm.getSeletor().getStringList("Seletores.Seletores." + s + ".lore")) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    l = PlaceholderAPI.setPlaceholders(p, l);
                }
                l = l.replace("&", "§");
                lore.add(l);
            }
            meta.setLore(lore);
            if (cm.getSeletor().getBoolean("Seletores.Seletores." + s + ".glow")) {
                meta.addEnchant(Enchantment.DURABILITY, 0, true);
            }

            Item.setItemMeta(meta);

            if (Item.isSimilar(item)) {
                return cm.getSeletor().getInt("Seletores.Seletores." + s + ".slot") - 1;
            }

        }

        return 0;
    }


    public void colocarItens(Player p) {
        for (ItemStack item : this.getItens(p)) {
            p.getInventory().setItem(getSlot(item, p), item);

        }

    }

    public void colocarEJ(Player p) {
        if (cm.getSeletor().getBoolean("EsconderJogadores.ativado")) {
            p.getInventory().setItem(cm.getSeletor().getInt("EsconderJogadores.Item.slot") - 1, this.getEJ(p).get(0));
        }
    }

    public void removerItens(Player p) {
        List<ItemStack> itens = this.getItens(p);
        for (ItemStack item : itens) {
            if (p.getInventory().contains(item)) {
                p.getInventory().removeItem(item);
            }
        }
    }

    public void removerEJ(Player p) {
        for (ItemStack item : this.getEJ(p)) {
            if (p.getInventory().contains(item)) {
                p.getInventory().removeItem(item);
            }
        }
    }

    public void trocarItem(Player p, ItemStack item) {
        if (this.getEJ(p).get(0).isSimilar(item)) {
            for (int i = 0; i < p.getInventory().getSize() - 1; i++) {
                if (p.getInventory().getItem(i) != null && p.getInventory().getItem(i).isSimilar(item)) {
                    p.getInventory().setItem(i, this.getEJ(p).get(1));
                }
            }
        } else if (this.getEJ(p).get(1).isSimilar(item)) {
            for (int i = 0; i < p.getInventory().getSize() - 1; i++) {
                if (p.getInventory().getItem(i) != null && p.getInventory().getItem(i).isSimilar(item)) {
                    p.getInventory().setItem(i, this.getEJ(p).get(0));
                }
            }
        }
    }

}
