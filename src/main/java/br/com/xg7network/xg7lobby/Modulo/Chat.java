package br.com.xg7network.xg7lobby.Modulo;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class Chat extends Module implements Listener {

    static HashMap<UUID, List<String>> messages = new HashMap<>();
    static HashMap<UUID, String> message = new HashMap<>();

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(cm.getConfig().getInt("anti-spam.cooldown"), TimeUnit.SECONDS).build();
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
            if (this.cooldown.asMap().containsKey(p.getUniqueId()) && this.cooldown.asMap().get(p.getUniqueId()) > System.currentTimeMillis()) {
                e.setCancelled(true);
                long distancia = this.cooldown.asMap().get(p.getUniqueId()) - System.currentTimeMillis();
                va.mandarMensagem(cm.getMessage().getString("events.message-cooldown").replace("[SECONDS]", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(distancia))), p);
            } else {
                this.cooldown.put(p.getUniqueId(), System.currentTimeMillis() + cm.getConfig().getLong("anti-spam.cooldown") * 1000L);
                if (messages.containsKey(p.getUniqueId())) {

                    if (messages.get(p.getUniqueId()).size() >= cm.getConfig().getInt("anti-spam.tolerance")) {
                        e.setCancelled(true);

                        va.mandarMensagem(cm.getMessage().getString("events.spam"), p);

                    } else {
                        if (message.containsKey(p.getUniqueId()) && message.get(p.getUniqueId()).contains(e.getMessage())) {
                            e.setCancelled(true);
                            va.mandarMensagem(cm.getMessage().getString("events.repeated-message"), p);
                        } else {
                            messages.get(p.getUniqueId()).add(e.getMessage());
                            message.put(p.getUniqueId(), e.getMessage());
                        }
                    }


                }
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        List<String> lista = new ArrayList<>();
        messages.put(p.getUniqueId(), lista);
        message.put(p.getUniqueId(), "");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        messages.remove(p.getUniqueId());
        message.remove(p.getUniqueId());
    }

    @Override
    public void onEnable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            List<String> lista = new ArrayList<>();
            messages.put(p.getUniqueId(), lista);
            message.put(p.getUniqueId(), "");
        }
        Bukkit.getScheduler().runTaskTimer(this.getPlugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (messages.containsKey(p.getUniqueId())) {
                    if (messages.get(p.getUniqueId()).size() > 0) {
                        List<String> lista = new ArrayList<>();
                        messages.put(p.getUniqueId(), lista);
                    }
                }
            }
        }, 0, cm.getConfig().getInt("anti-spam.spam-cooldown"));
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            messages.remove(p.getUniqueId());
            message.remove(p.getUniqueId());
        }

    }
}
