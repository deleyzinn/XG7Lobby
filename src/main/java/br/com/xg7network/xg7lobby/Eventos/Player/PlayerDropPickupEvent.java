package br.com.xg7network.xg7lobby.Eventos.Player;

import br.com.xg7network.xg7lobby.Configs.PermissionType;
import br.com.xg7network.xg7lobby.XG7Lobby;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import static br.com.xg7network.xg7lobby.XG7Lobby.*;

public class PlayerDropPickupEvent implements Listener {

    @EventHandler
    public void PlayerDropEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
            if (!cm.getConfig().getBoolean("drop-items")) {
                if (!(p.hasPermission(PermissionType.ITENS_JOGAR.getPerm()))) {
                    e.setCancelled(true);
                    va.mandarMensagem(cm.getMessage().getString("events.permission-drop"), p);
                }
            }
        }
    }

    @EventHandler
    public void PlayerPickupEvent (EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (cm.getConfig().getStringList("enabled-worlds").contains(p.getWorld().getName())) {
                if (cm.getConfig().getBoolean("pickup-items")) {
                    if (!(p.hasPermission(PermissionType.ITENS_PEGAR.getPerm()))) {
                        e.setCancelled(true);
                        va.mandarMensagem(cm.getMessage().getString("events.permission-pickup"), p);
                    }
                }
            }
        }
    }
}
