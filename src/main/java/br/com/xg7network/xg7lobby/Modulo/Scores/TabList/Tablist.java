package br.com.xg7network.xg7lobby.Modulo.Scores.TabList;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class Tablist extends Module implements Listener {

    int tabl;

    public Tablist(XG7Lobby plugin) {
        super(plugin);
    }

    @Override
    public void onEnable() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (cm.getConfig().getBoolean("scores.tablist.enabled")) {
                if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                    colocarTablist(p);
                }
            }
            Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
                updateTablist(p);
            }, 0, cm.getConfig().getInt("scores.update"));
        }
    }

    @Override
    public void onDisable() {

        Bukkit.getScheduler().cancelTask(tabl);
        for (Player p : Bukkit.getOnlinePlayers()) {
            this.removerTablist(p);
        }


    }


    List<String> getTablist(Player p) {

        List<String> s = new ArrayList<>();
        String rodape = String.join("\n", cm.getConfig().getStringList("scores.tablist.footer"));
        String cabecalho = String.join("\n", cm.getConfig().getStringList("scores.tablist.header"));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            rodape = PlaceholderAPI.setPlaceholders(p, rodape);
            cabecalho = PlaceholderAPI.setPlaceholders(p, cabecalho);
        }
        s.add(ChatColor.translateAlternateColorCodes('&', cabecalho));
        s.add(ChatColor.translateAlternateColorCodes('&', rodape));
        return s;
    }

    void colocarTablist(Player p) {
        updateTablist(p);
    }

    void removerTablist(Player p) {
        TabListSender.sendTablist(p, "", "");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("scores.tablist.enabled")) {
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                Bukkit.getScheduler().runTaskLater(getPlugin(), () -> colocarTablist(p), 5);
            }
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        removerTablist(e.getPlayer());

    }
    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e) {

        World to = e.getTo().getWorld();
        Player p = e.getPlayer();


        Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
            if (cm.getConfig().getStringList("enabled-worlds").contains(to.getName())) {
                this.colocarTablist(p);
            } else {
                this.removerTablist(p);
            }
        }, 20);

    }

    void updateTablist(Player p) {

        if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
            TabListSender.sendTablist(p, this.getTablist(p).get(0), this.getTablist(p).get(1));
        }
    }

}
