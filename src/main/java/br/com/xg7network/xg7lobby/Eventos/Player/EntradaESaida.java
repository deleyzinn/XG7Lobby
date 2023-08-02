package br.com.xg7network.xg7lobby.Eventos.Player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.ac;
import static br.com.xg7network.xg7lobby.XG7Lobby.cm;

public class EntradaESaida implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        ac.executar(cm.getConfig().getStringList("EventosDeEntrada.ações"), p);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("MensagemDeSaida").replace("[PLAYER]", e.getPlayer().getName())));

    }

}
