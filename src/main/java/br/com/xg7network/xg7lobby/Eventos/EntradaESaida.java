package br.com.xg7network.xg7lobby.Eventos;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.ac;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class EntradaESaida implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        p.setAllowFlight(true);
        String mensagem = cm.getConfig().getString("MensagemDeEntrada").replace("[PLAYER]", e.getPlayer().getName());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            mensagem = PlaceholderAPI.setPlaceholders(p, mensagem);
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', mensagem));
        ac.executar(cm.getConfig().getStringList("EventosDeEntrada.acoes"), p);
        if (cm.getConfig().getBoolean("tp-quando-entrar")) {
            if (cm.getData().getString("lobby.mundo") == null) {
                p.teleport(p.getWorld().getSpawnLocation());
            } else {
                String lobbyWN = cm.getData().getString("lobby.mundo");
                if (lobbyWN != null) {
                    World w = Bukkit.getWorld(lobbyWN);
                    p.teleport(new Location(
                            w,
                            cm.getData().getDouble("lobby.x"),
                            cm.getData().getDouble("lobby.y"),
                            cm.getData().getDouble("lobby.z"),
                            (float) cm.getData().getDouble("lobby.yaw"),
                            (float) cm.getData().getDouble("lobby.pitch")));

                }
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        String mensagem = cm.getConfig().getString("MensagemDeSaida").replace("[PLAYER]", e.getPlayer().getName());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            mensagem = PlaceholderAPI.setPlaceholders(p, mensagem);
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', mensagem));

    }

}
