package br.com.xg7network.xg7lobby.Eventos;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;

import java.util.Locale;

import static br.com.xg7network.xg7lobby.XG7Lobby.cm;
import static br.com.xg7network.xg7lobby.XG7Lobby.va;

public class Chat implements Listener {

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

}
