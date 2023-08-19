package br.com.xg7network.xg7lobby.Modulo.Scores;

import br.com.xg7network.xg7lobby.Modulo.Module;
import br.com.xg7network.xg7lobby.XG7Lobby;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    List<String> getTablist(Player p) {
        List<String> s = new ArrayList<>();
        String rodape = String.join("\n", cm.getConfig().getStringList("scores.tablist.rodape"));
        String cabecalho = String.join("\n", cm.getConfig().getStringList("scores.tablist.cabecalho"));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            rodape = PlaceholderAPI.setPlaceholders(p, rodape);
            cabecalho = PlaceholderAPI.setPlaceholders(p, cabecalho);
        }
        s.add(ChatColor.translateAlternateColorCodes('&', cabecalho));
        s.add(ChatColor.translateAlternateColorCodes('&', rodape));
        return s;
    }

    void colocarTablist(Player p) {
        List<String> tablist = this.getTablist(p);
        p.setPlayerListHeader(tablist.get(0));
        p.setPlayerListFooter(tablist.get(1));
    }

    void removerTablist(Player p) {
        p.setPlayerListHeader("");
        p.setPlayerListFooter("");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("scores.tablist.ativado")) {
            if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {
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
            if (cm.getConfig().getStringList("mundos-ativados").contains(to.getName())) {
                this.colocarTablist(p);
            } else {
                this.removerTablist(p);
            }
        }, 1);

    }

    void updateTablist(Player p) {

        if (cm.getConfig().getStringList("mundos-ativados").contains(p.getWorld().getName())) {


            List<String> tablist = this.getTablist(p);
            p.setPlayerListHeader(tablist.get(0));
            p.setPlayerListFooter(tablist.get(1));
        }
    }


    @Override
    public void onEnable() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (cm.getConfig().getBoolean("scores.tablist.ativado")) {
                colocarTablist(p);
            }
            Bukkit.getScheduler().runTaskTimer(getPlugin(), () -> {
                updateTablist(p);
            }, 0, cm.getConfig().getInt("scores.atualizacao"));
        }
    }

    @Override
    public void onDisable() {

        Bukkit.getScheduler().cancelTask(tabl);
        for (Player p : Bukkit.getOnlinePlayers()) {
            this.removerTablist(p);
        }


    }
}
