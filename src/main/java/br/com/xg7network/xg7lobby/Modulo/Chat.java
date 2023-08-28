package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class Chat extends Module implements Listener {

    HashMap<UUID, Integer> messages = new HashMap<>();
    public Chat(XG7Lobby plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBadWords(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("block-swearing.enabled")) {
            String mensagemSuspeita = e.getMessage();
            if (cm.getConfig().getStringList("block-swearing.blocked-words").contains(mensagemSuspeita.toLowerCase(Locale.ROOT))){
                e.setCancelled(!p.hasPermission(PermissionType.CHAT_PALAVRAS.getPerm()));
                va.mandarMensagem(cm.getMessage().getString("events.badword"), p);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("block-commands.enabled")) {
            String comandobloqueado = e.getMessage();
            if (cm.getConfig().getStringList("block-commands.commands-blocked").contains(comandobloqueado)){
                e.setCancelled(!p.hasPermission(PermissionType.CHAT_COMANDOS.getPerm()));
                va.mandarMensagem(cm.getMessage().getString("events.command-block"), p);
            }
        }
    }

    @EventHandler
    public void onSpam(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getBoolean("anti-spam.enabled")) {
            if (messages.containsKey(p.getUniqueId())) {

                if (messages.get(p.getUniqueId()) >= cm.getConfig().getInt("anti-spam.tolerance")) {
                    e.setCancelled(true);
                    va.mandarMensagem(cm.getMessage().getString("events.spam"), p);

                } else {
                    messages.put(p.getUniqueId(), messages.get(p.getUniqueId()) + 1);
                }

                System.out.println(messages);

            } else {
                System.out.println(false);
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        messages.put(p.getUniqueId(), 0);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        messages.remove(p.getUniqueId());
    }

    @Override
    public void onEnable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            messages.put(p.getUniqueId(), 0);
        }
        Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (messages.containsKey(p.getUniqueId())) {
                    if (messages.get(p.getUniqueId()) > 0) {
                        System.out.println("a");
                        messages.put(p.getUniqueId(), messages.get(p.getUniqueId()) - 2);
                    }
                }
            }
        }, 0, cm.getConfig().getInt("anti-spam.spam-cooldown"));
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            messages.remove(p.getUniqueId());
        }

    }
}
